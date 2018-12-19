package android.small.indeedfortunate.xqsmallnormal.activity.login.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.small.indeedfortunate.xqsmallnormal.BaseActivity;
import android.small.indeedfortunate.xqsmallnormal.ForgetPSWActivity;
import android.small.indeedfortunate.xqsmallnormal.R;
import android.small.indeedfortunate.xqsmallnormal.activity.login.LoginActivity;
import android.small.indeedfortunate.xqsmallnormal.api.ApiManager;
import android.small.indeedfortunate.xqsmallnormal.bean.UserBean;
import android.small.indeedfortunate.xqsmallnormal.model.net.OkHttpUtils;
import android.small.indeedfortunate.xqsmallnormal.util.ToastUtils;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity  {

    private TextView mCloseRegistTv;
    private Toolbar mToolbar;
    private EditText mEtPhone;
    private EditText mEtPassword;
    private TextView mTvGainAuthcode;
    private Button mRegisterBtn;
    private TextView mThatClause;
    private EditText mNewPassword;
    String authcode,password;
    String phone;
    final OkHttpClient client = new OkHttpClient();
    private  Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what==1){
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息",ReturnMessage);
                final UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
                final String AA = userBean.getMsg();
                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 */
                Log.i("MSGhahahha", AA);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initData();
    }

    private void initView() {
        mCloseRegistTv = (TextView) findViewById(R.id.close_regist_Tv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mTvGainAuthcode = (TextView) findViewById(R.id.Tv_gain_authcode);
        mRegisterBtn = (Button) findViewById(R.id.Register_Btn);
        mThatClause = (TextView) findViewById(R.id.That_clause);

        mNewPassword = (EditText) findViewById(R.id.New_password);

        mCloseRegistTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    private void initData() {
        mTvGainAuthcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mEtPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("phone", phone);
                linkedHashMap.put("smsType", "register");
                OkHttpUtils.postJson(ApiManager.SENDSMS,linkedHashMap,
                        new OkHttpUtils.ResultCallback<String>() {
                            @Override
                            public void onSuccess(int code, String response) {


                                Log.e("发送验证码",response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(RegisterActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int code, Exception e) {

                            }
                        });

            }
        });
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void submit() {
        // validate
         phone = mEtPhone.getText().toString();
         authcode = mEtPassword.getText().toString();
         password = mNewPassword.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(authcode)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请设置新密码", Toast.LENGTH_SHORT).show();
            return;
        }


        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("appLoginname", phone);
        linkedHashMap.put("appPasswd", password);
        linkedHashMap.put("smsCode", authcode);
        OkHttpUtils.postJson(ApiManager.REGISTER,linkedHashMap,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(int code, String response) {


                        Log.e("注册",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("message");
                            String code1 = jsonObject.getString("code");
                           // Toast.makeText(RegisterActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                            if (code1.equals("0"))
                            {
                                finish();
                                Toast.makeText(RegisterActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                Toast.makeText(RegisterActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int code, Exception e) {

                    }
                });





    }
        /**
         * post请求后台
         * @param phone
         * @param password
         */
    private void postRequest(String phone,String authcode,String password)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("username",phone)
                .add("authcode",authcode)
                .add("password",password)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://**************/login?")
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();

                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    }


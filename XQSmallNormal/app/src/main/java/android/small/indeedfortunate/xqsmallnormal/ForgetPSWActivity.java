package android.small.indeedfortunate.xqsmallnormal;

import android.content.Context;
import android.content.Intent;
import android.small.indeedfortunate.xqsmallnormal.activity.login.register.RegisterActivity;
import android.small.indeedfortunate.xqsmallnormal.api.ApiManager;
import android.small.indeedfortunate.xqsmallnormal.model.net.OkHttpUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class ForgetPSWActivity extends BaseActivity {


    private EditText mEtPhone;
    private EditText mEtPassword;
    private EditText mNewPassword;
    String authcode,password;
    String phone;
    TextView forget_hqmm;
    Button button;




    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ForgetPSWActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

        initView();
        initData();
    }



    private void initView() {

        button = findViewById(R.id.submit);
        forget_hqmm = findViewById(R.id.forget_hqmm);
        mEtPhone = findViewById(R.id.et_phone);
        mEtPassword = findViewById(R.id.et_password);
        mNewPassword = findViewById(R.id.newpass);
    }

    private void initData() {

        //获取验证码点击时间

        forget_hqmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mEtPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(ForgetPSWActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("phone", phone);
                linkedHashMap.put("smsType", "forgot");
                OkHttpUtils.postJson(ApiManager.SENDSMS,linkedHashMap,
                        new OkHttpUtils.ResultCallback<String>() {
                            @Override
                            public void onSuccess(int code, String response) {


                                Log.e("找回密码验证码",response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(ForgetPSWActivity.this, msg+"", Toast.LENGTH_SHORT).show();
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


        //提交点击时间
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mEtPhone.getText().toString();
                authcode = mEtPassword.getText().toString();
                password = mNewPassword.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(ForgetPSWActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(authcode)) {
                    Toast.makeText(ForgetPSWActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(ForgetPSWActivity.this, "请设置新密码", Toast.LENGTH_SHORT).show();
                    return;
                }


                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("appLoginname", phone);
                linkedHashMap.put("appPasswd", password);
                linkedHashMap.put("smsCode", authcode);
                OkHttpUtils.postJson(ApiManager.RETURNPASS,linkedHashMap,
                        new OkHttpUtils.ResultCallback<String>() {
                            @Override
                            public void onSuccess(int code, String response) {


                                Log.e("商家找回密码",response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String msg = jsonObject.getString("message");
                                    String code1 = jsonObject.getString("code");
                                    if (code1.equals("0"))
                                    {
                                        finish();
                                        Toast.makeText(ForgetPSWActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                        return;
                                    }else {
                                        Toast.makeText(ForgetPSWActivity.this, msg+"", Toast.LENGTH_SHORT).show();
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
        });




    }
}

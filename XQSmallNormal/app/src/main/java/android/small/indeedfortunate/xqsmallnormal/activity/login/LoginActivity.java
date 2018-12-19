package android.small.indeedfortunate.xqsmallnormal.activity.login;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.small.indeedfortunate.xqsmallnormal.BaseActivity;
import android.small.indeedfortunate.xqsmallnormal.R;
import android.small.indeedfortunate.xqsmallnormal.activity.login.register.RegisterActivity;
import android.small.indeedfortunate.xqsmallnormal.api.ApiManager;
import android.small.indeedfortunate.xqsmallnormal.bean.UserBean;
import android.small.indeedfortunate.xqsmallnormal.ForgetPSWActivity;
import android.small.indeedfortunate.xqsmallnormal.model.net.OkHttpUtils;
import android.small.indeedfortunate.xqsmallnormal.util.DeviceMessageUtil;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    private Button login_button;
    private TextView mBusinessRegist;
    private Toolbar mToolbar;
    private EditText mLoginTelphone;
    private EditText mNewAuth;
    private TextView mTvGainAuthcode;
    private LinearLayout mLlAuthcode;
    private EditText mLoginUsername;
    private EditText mNewPassword;
    private TextView mRetrievePassword;
    private LinearLayout mLlAuthpassword;
    private Button mLoginButton;
    private String telphone;
    private String auth;
    private String username;
    private String password, spPsw;
    private String userName;
    private SharedPreferences sp;
    private RadioButton mRbCodelogin;
    private RadioButton mRbAccountlogin;
    int type;

    final OkHttpClient client = new OkHttpClient();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                String ReturnMessage = (String) msg.obj;
                Log.i("获取返回的信息",ReturnMessage);
                final UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
                final String AA = userBean.getMsg();
                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 */
                Log.i("MSGhahaha",AA);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("info1.txt", MODE_PRIVATE);
        initView();


        mRetrievePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //找回密码控件的点击事件
                Intent intent = new Intent(LoginActivity.this, ForgetPSWActivity.class);
                startActivity(intent);
            }
        });

        //短信验证码点击时间
        mTvGainAuthcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telphone = mLoginTelphone.getText().toString();
                if (TextUtils.isEmpty(telphone)) {
                    Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("phone", telphone);
                linkedHashMap.put("smsType", "login");
                OkHttpUtils.postJson(ApiManager.SENDSMS,linkedHashMap,
                        new OkHttpUtils.ResultCallback<String>() {
                            @Override
                            public void onSuccess(int code, String response) {


                                Log.e("登陆发送验证码",response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(LoginActivity.this, msg+"", Toast.LENGTH_SHORT).show();
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

        //登录按钮的点击事件
        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //开始登录，获取用户名和密码 getText().toString().trim();
                telphone = mLoginTelphone.getText().toString();
                auth = mNewAuth.getText().toString();
                username = mLoginUsername.getText().toString();
                password = mNewPassword.getText().toString();
                //获取相关参数
                username=mLoginUsername.getText().toString();
                password=mNewPassword.getText().toString();

              //  Toast.makeText(LoginActivity.this, type+"", Toast.LENGTH_SHORT).show();


                if (type==0){
                    if ( TextUtils.isEmpty(telphone))
                    {
                        Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ( TextUtils.isEmpty(auth))
                    {
                        Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("appLoginname", telphone);
                    linkedHashMap.put("smsCode", auth);
                    linkedHashMap.put("mobileToken", DeviceMessageUtil.getEI_SI("imei",LoginActivity.this));
                    linkedHashMap.put("deviceToken", DeviceMessageUtil.getAndroidId(LoginActivity.this));
                    linkedHashMap.put("loginIp", getClientIP());
                    linkedHashMap.put("clientName", DeviceMessageUtil.getDeviceName());
                    linkedHashMap.put("clientType", Build.MODEL);
                    OkHttpUtils.postJson(ApiManager.DXLOGIN,linkedHashMap,
                            new OkHttpUtils.ResultCallback<String>() {
                                @Override
                                public void onSuccess(int code, String response) {


                                    Log.e("发送验证码",response);
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(LoginActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(int code, Exception e) {

                                }
                            });
                }else {

                    if ( TextUtils.isEmpty(username))
                    {
                        Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ( TextUtils.isEmpty(password))
                    {
                        Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("appLoginname", username);
                    linkedHashMap.put("appPasswd", password);
                    linkedHashMap.put("mobileToken", DeviceMessageUtil.getEI_SI("imei",LoginActivity.this));
                    linkedHashMap.put("deviceToken", DeviceMessageUtil.getAndroidId(LoginActivity.this));
                    linkedHashMap.put("loginIp", getClientIP());
                    linkedHashMap.put("clientName", DeviceMessageUtil.getDeviceName());
                    linkedHashMap.put("clientType", Build.MODEL);
                    OkHttpUtils.postJson(ApiManager.ZHLOGIN,linkedHashMap,
                            new OkHttpUtils.ResultCallback<String>() {
                                @Override
                                public void onSuccess(int code, String response) {


                                    Log.e("账号密码登陆",response);
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(LoginActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(int code, Exception e) {

                                }
                            });



                }



            }
        });


    }
    /**
     * 获取安卓设备当前的IP地址（有线或无线）
     *
     * @return
     */
    private String getClientIP() {

        try {
            // 获取本地设备的所有网络接口
            Enumeration<NetworkInterface> enumerationNi = NetworkInterface
                    .getNetworkInterfaces();
            while (enumerationNi.hasMoreElements()) {
                NetworkInterface networkInterface = enumerationNi.nextElement();
                String interfaceName = networkInterface.getDisplayName();
                Log.i("tag", "网络名字" + interfaceName);

                // 如果是有限网卡
                if (interfaceName.equals("eth0")) {
                    Enumeration<InetAddress> enumIpAddr = networkInterface
                            .getInetAddresses();

                    while (enumIpAddr.hasMoreElements()) {
                        // 返回枚举集合中的下一个IP地址信息
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        // 不是回环地址，并且是ipv4的地址
                        if (!inetAddress.isLoopbackAddress()
                                && inetAddress instanceof Inet4Address) {
                            Log.i("tag", inetAddress.getHostAddress() + "   ");

                            return inetAddress.getHostAddress();
                        }
                    }
                    //  如果是无限网卡
                } else if (interfaceName.equals("wlan0")) {
                    Enumeration<InetAddress> enumIpAddr = networkInterface
                            .getInetAddresses();

                    while (enumIpAddr.hasMoreElements()) {
                        // 返回枚举集合中的下一个IP地址信息
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        // 不是回环地址，并且是ipv4的地址
                        if (!inetAddress.isLoopbackAddress()
                                && inetAddress instanceof Inet4Address) {
                            Log.i("tag", inetAddress.getHostAddress() + "   ");

                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";

    }


    public void onClick(View view) {
        switch (view.getId()){
            //跳转至商户注册页面
            case R.id.Business_regist:
                //为了跳转到注册界面，并实现注册功能
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            //验证码登录
            case R.id.Rb_codelogin:
                mLoginTelphone.setVisibility(View.VISIBLE);
                mNewAuth.setVisibility(View.VISIBLE);
                mTvGainAuthcode.setVisibility(View.VISIBLE);
                mLoginUsername.setVisibility(View.GONE);
                mNewPassword.setVisibility(View.GONE);
                mRetrievePassword.setVisibility(View.GONE);
                type = 0;

                break;
            //账号密码登录
            case R.id.Rb_accountlogin:
                mLoginTelphone.setVisibility(View.GONE);
                mNewAuth.setVisibility(View.GONE);
                mTvGainAuthcode.setVisibility(View.GONE);
                mLoginUsername.setVisibility(View.VISIBLE);
                mNewPassword.setVisibility(View.VISIBLE);
                mRetrievePassword.setVisibility(View.VISIBLE);
                type = 1;
                break;
            //获取验证码
            case R.id.new_auth:

                break;
            //忘记密码
            case R.id.retrieve_password:
                startActivity(ForgetPSWActivity.newIntent(this));
                break;
        }
    }




    private void initView() {
        mBusinessRegist = (TextView) findViewById(R.id.Business_regist);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLoginTelphone = (EditText) findViewById(R.id.login_telphone);
        mNewAuth = (EditText) findViewById(R.id.new_auth);
        mTvGainAuthcode = (TextView) findViewById(R.id.Tv_gain_authcode);
        mLlAuthcode = (LinearLayout) findViewById(R.id.ll_authcode);
        mLoginUsername = (EditText) findViewById(R.id.login_username);
        mNewPassword = (EditText) findViewById(R.id.new_password);
        mRetrievePassword = (TextView) findViewById(R.id.retrieve_password);
        mLlAuthpassword = (LinearLayout) findViewById(R.id.ll_authpassword);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mRbCodelogin = (RadioButton) findViewById(R.id.Rb_codelogin);
        mRbAccountlogin = (RadioButton) findViewById(R.id.Rb_accountlogin);
        telphone = mLoginTelphone.getText().toString().trim();
        auth = mNewAuth.getText().toString().trim();

//        if (TextUtils.isEmpty(telphone)) {
//            Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
//            return;
//        } else if (TextUtils.isEmpty(auth)) {
//            Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
//            return;
//        } else if (!TextUtils.isEmpty(telphone) || !TextUtils.isEmpty(auth)){
//
//        }
//        username = mLoginUsername.getText().toString().trim();
//        if (TextUtils.isEmpty(username)) {
//            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        password = mNewPassword.getText().toString().trim();
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
//            return;
//        }
    }
    /**
     * 点击检查 相机、打电话 权限
     */
    private void applyPermission() {
        PermissionUtil permissionUtil = new PermissionUtil(LoginActivity.this);
        permissionUtil.requestPermissions(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                new PermissionListener() {
                    @Override
                    void onGranted() {

                    }

                    @Override
                    void onDenied(List<String> deniedPermission) {

                    }

                    @Override
                    void onShouldShowRationale(List<String> deniedPermission) {

                    }
                });

    }


    private long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Misc.alert("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                ImageLoader.clearMemory();
                AppManager.getAppManager().AppExit(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    public class HttpConfig{
        public  String url= "https://www.zhaoapi.cn/user/login";
        public  String reg_url = "https://www.zhaoapi.cn/user/reg";
    }


}

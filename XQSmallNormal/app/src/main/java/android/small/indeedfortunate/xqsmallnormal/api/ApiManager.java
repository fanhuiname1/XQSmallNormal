package android.small.indeedfortunate.xqsmallnormal.api;

/**
 * Created by fanhui on 2018/12/17.
 */

public class ApiManager {
    public static final String ALL_URL = "http://121.41.18.71:14500/";


    public static String getBaseUrl()
    {
        return ALL_URL;
    }


    /**
     * 账号密码登陆
     *
     */

    public static final String ZHLOGIN = getBaseUrl() + "auth/merchant/loginByPassword";



    /**
     * 短信验证码登陆
     *
     */

    public static final String DXLOGIN = getBaseUrl() + "auth/merchant/loginBySms";



    /**
     * 手机号注册
     *
     */

    public static final String REGISTER = getBaseUrl() + "xqx/merchant/register";


    /**
     * 发送验证码
     *
     */

    public static final String SENDSMS = getBaseUrl() + "xqx/merchant/sendSMS";


    /**
     * 商家找回密码
     *
     */

    public static final String RETURNPASS = getBaseUrl() + "auth/merchant/retrievePasswd";



    /**
     * 省市区三级联动
     *
     */

    public static final String DICTADDRESS = getBaseUrl() + "xqx/merchant/findDictAddress";



}

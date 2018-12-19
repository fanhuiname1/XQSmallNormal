package android.small.indeedfortunate.xqsmallnormal.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.small.indeedfortunate.xqsmallnormal.App;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by 旧城丷 on 2018/12/12.
 */

class Misc {

    /**
     * 配置文件名，该文件用于保存基本的软件数据
     */
    public static final String FILE_BASE_SETTINGS = "base_settings";


    public static class LockerConstants {
        /**
         * 配置文件字段 - 用户是否添加了widget true/false
         */
        public static final String IS_LOCKER_WIDGET_ENABLE = "is_locker_widget_enable";
    }

    /**
     * 获取string
     *
     * @return
     */
    public static String getStrValue(int id) {
        try {
            return App.getApplication().getString(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSDcardRootPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Context context, Double dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static boolean isDecimal(CharSequence string) {
        if (string == null)
            return false;
        String regEx = "^-?\\d+\\.\\d+$";
        return Pattern.compile(regEx).matcher(string).matches();
    }

    public static View inflate(Context context, int id) {
        return LayoutInflater.from(context).inflate(id, null);
    }

    /**
     * 操作系统版本号
     */
    public static String getOSVertion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 设备型号
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    public static String toString(Object object) {
        String result = "错误：null";
        if (object != null) {
            result = object.toString();
        }
        return result;
    }

    /**
     * 将长时间格式字符串转换为字符串,默认为yyyy-MM-dd HH:mm:ss
     *
     * @param milliseconds long型时间,单位是微秒
     * @param dataFormat   需要返回的时间格式，例如： yyyy-MM-dd， yyyy-MM-dd HH:mm:ss， yyyy-MM-dd'T'HH:mm:ss.SSSZ
     * @return dataFormat格式的时间结果字符串
     * @see "http://download.oracle.com/technetwork/java/javase/6/docs/zh/api/java/text/SimpleDateFormat.html"
     */
    public static String dateFormat(long milliseconds, String dataFormat) {
        if (TextUtils.isEmpty(dataFormat)) {
            dataFormat = "yyyy-MM-dd HH:mm:ss";
        }
        Date date = new Date(milliseconds * 1l);
        SimpleDateFormat formatter = new SimpleDateFormat(dataFormat, Locale.CHINA);
        return formatter.format(date);
    }


    /**
     * float类型，保留N个小数
     */
    public static String scaleFloatAsRequest(float scale, int count) {
        final String format = "%." + count + "f";
        String result;
        try {
            result = String.format(format, scale);
        } catch (Exception e) {
            e.printStackTrace();
            return scale + "";
        }
        return result;
    }


    /**
     * float，设置不显示小数点后面的无效数字
     */

    public static String scale(float num, int count){
        String number = num + "";
        if (!number.contains(".")){
            return number;
        } else {
            String decimalNum = number.split("\\.")[1];
            if (decimalNum.length() < 2){
                if (decimalNum.equals("0")){
                    return scaleFloatAsRequest(num, 0);
                }
                return number;
            } else {
                if (decimalNum.equals("00")){
                    return scaleFloatAsRequest(num, 0);
                }
                return scaleFloatAsRequest(num, count);
            }
        }
    }

    /**
     * double，保留N个小数
     */
    public static String scaleDoubleAsRequest(double scale, int count) {
        final String format = "%." + count + "f";
        String result;
        try {
            result = String.format(format, scale);
        } catch (Exception e) {
            e.printStackTrace();
            return scale + "";
        }
        return result;
    }

    /**
     * double，设置不显示小数点后面的无效数字
     */

    public static String scale(double num, int count){
        String number = num + "";
        if (!number.contains(".")){
            return number;
        } else {
            String decimalNum = number.split("\\.")[1];
            if (decimalNum.length() < 2){
                if (decimalNum.equals("0")){
                    return scaleDoubleAsRequest(num, 0);
                }
                return number;
            } else {
                if (decimalNum.equals("00")){
                    return scaleDoubleAsRequest(num, 0);
                }
                return scaleDoubleAsRequest(num, count);
            }
        }
    }

    //金额format 可以按照需要统一设置
    public static String formatDoubleMoney(double scale) {
        return scale(scale, 2);
    }

    public static int[] getScreenDisplay(Context context) {
        int[] result = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        result[0] = dm.widthPixels;
        result[1] = dm.heightPixels;
        if (result[0] > result[1]) {
            Log.i("getScreenDisplay", "宽:" + result[0] + " > 高:" + result[1]);
            int tempDisplay = result[0];
            result[0] = result[1];
            result[1] = tempDisplay;
        }
        Log.d("houbin.li display", result[0] + ":::" + result[1]);
        return result;
    }

    /**
     * 获取屏幕分辨率
     *
     * @return int[]数组，长度为2
     */
    public static int[] getScreenDisplay(Activity activity) {
        int[] result = getScreenDisplay();
        return result;
    }

    public static int[] getScreenDisplay() {
        int[] result = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) App.getApplication().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        result[0] = dm.widthPixels;
        result[1] = dm.heightPixels;
        if (result[0] > result[1]) {
            Log.i("getScreenDisplay", "宽:" + result[0] + " > 高:" + result[1]);
            int tempDisplay = result[0];
            result[0] = result[1];
            result[1] = tempDisplay;
        }
        Log.d("houbin.li display", result[0] + ":::" + result[1]);
        return result;
    }

    //顶部的alert
    public static void alertLogin(int id) {
        alert(null, id);
    }

    //子线程不能调用
    public static void alertLogin(String str) {
        alert(null, str);
    }

    public static void alertPager(int id) {
        alert(null, id);
    }

    //子线程不能调用
    public static void alertPager(String str) {
        alert(null, str);
    }

    public static void alert(Context context, String str) {
        Toast.makeText(App.getApplication(), str, Toast.LENGTH_SHORT).show();
    }

    public static void alert(int id) {
        alert(null, id);
    }

    public static void alert(Context context, int id) {
        Toast.makeText(App.getApplication(), id, Toast.LENGTH_SHORT).show();
    }

    public static void alert(String str) {
        alert(null, str);
    }

    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }
}

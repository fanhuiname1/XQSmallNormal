package android.small.indeedfortunate.xqsmallnormal;

import android.app.Application;
import android.small.indeedfortunate.xqsmallnormal.util.ToastUtils;
import android.small.indeedfortunate.xqsmallnormal.util.log.LogUtils;

//哈哈哈
public class App extends Application {

    private static App mApplication;

    public static App getApplication() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        ToastUtils.init(this);
        LogUtils.init(true);
    }
}

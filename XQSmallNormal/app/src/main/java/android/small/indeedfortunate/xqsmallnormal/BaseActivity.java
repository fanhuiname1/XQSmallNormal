package android.small.indeedfortunate.xqsmallnormal;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends FragmentActivity {

    private boolean activityRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setupTransparencyStatus();
        ATStatusBarUtils.StatusBarLightMode(this);
    }
    /**
     * 设置透明状态栏
     */
    protected void setupTransparencyStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
        }
    }
    protected boolean isDestroyed;
    public static boolean assertFinishingOrDestoryed(Activity activity) {
        if (activity == null) return true;

        if (activity instanceof BaseActivity) {
            return !((BaseActivity) activity).isActivityRunning();
        }

        return activity.isFinishing() || assertDestroyed(activity);
    }

    private static boolean assertDestroyed(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            return true;
        }
        return false;
    }

    public boolean isActivityRunning() {
        return !isFinishing() && !isDestroyed;
    }
}



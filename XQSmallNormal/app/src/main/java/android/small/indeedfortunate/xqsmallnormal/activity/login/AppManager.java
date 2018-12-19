package android.small.indeedfortunate.xqsmallnormal.activity.login;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.small.indeedfortunate.xqsmallnormal.MainActivity;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 旧城丷 on 2018/12/12.
 */

public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;
    private List<Activity> mList = new LinkedList<Activity>();
    private AppManager() {
    }
    // add Activity
    public void putActivity(Activity activity) {
        mList.add(activity);
    }

    public void clearActivity() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        return instance;
    }

    /**
     * 添加Activity到堆�?
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public Activity indexOfActivity(int index) {
        if (index < 0 || index >= activityStack.size()) {
            return null;
        }
        return activityStack.elementAt(index);
    }

    /**
     * 获取当前Activity（堆栈中�?���?��压入的）
     */
    public Activity currentActivity() {
        try {
            return activityStack.lastElement();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 结束当前Activity（堆栈中�?���?��压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 栈内是否包含当前activity
     */
    public boolean containsActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 返回当前activity的索引号
     */
    public int currentActivityIndex() {
        return activityStack.size() - 1;
    }

    /**
     * 回到索引位置的Activity
     */
    public void finishActivity(int index) {
        if (index < 0 || index > activityStack.size() - 2)
            return;
        for (int i = activityStack.size() - 1; i > index; i--) {
            if (null != activityStack.get(i)) {
                if (activityStack.get(i).getClass() == MainActivity.class) {
                    continue;
                }
                finishActivity(activityStack.get(i));
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);

            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception ignored) {
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivitivies() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public int getActivityStackSize() {
        if (null == activityStack) {
            return 0;
        } else {
            return activityStack.size();
        }
    }
}

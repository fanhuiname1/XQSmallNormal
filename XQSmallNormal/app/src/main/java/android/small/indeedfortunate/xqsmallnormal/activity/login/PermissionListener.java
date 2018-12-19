package android.small.indeedfortunate.xqsmallnormal.activity.login;

import java.util.List;

/**
 * Created by 旧城丷 on 2018/12/12.
 */

public abstract class PermissionListener {
    /**
     * 权限全部已经授权
     */
    abstract void onGranted();

    /**
     * 权限被拒绝
     *
     * @param deniedPermission 被拒绝的权限List
     */

    abstract void onDenied(List<String> deniedPermission);

    /**
     * 权限被拒绝并且勾选了不在询问
     *
     * @param deniedPermission 勾选了不在询问的权限List
     */
    abstract void onShouldShowRationale(List<String> deniedPermission);
}

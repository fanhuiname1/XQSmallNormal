package android.small.indeedfortunate.xqsmallnormal.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 旧城丷 on 2018/12/11.
 */

public class NetWorkUtils {
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_WIFI = 2;
    public static final int NETWORK_TYPE_CMWAP = 3;
    public static final int NETWORK_TYPE_CMNET = 4;
    public static final int NETWORK_TYPE_CTNET = 5;
    public static final int NETWORK_TYPE_CTWAP = 6;
    public static final int NETWORK_TYPE_3GWAP = 7;
    public static final int NETWORK_TYPE_3GNET = 8;
    public static final int NETWORK_TYPE_UNIWAP = 9;
    public static final int NETWORK_TYPE_UNINET = 10;

    private Context context;
    private ConnectivityManager connManager;

    public NetWorkUtils(Context context) {
        this.context = context;
        connManager = (ConnectivityManager) this.context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    /**
     * 网络是否连接可用
     *
     * @return
     */
    public boolean isNetworkConnected() {

//        if (connManager == null) {
//            connManager = (ConnectivityManager) ItLanbaoLibApplication.getInstance()
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//        }

        if (connManager != null) {
            final NetworkInfo networkinfo = connManager.getActiveNetworkInfo();

            if (networkinfo != null) {
                return networkinfo.isConnected();
            }
        } else {
            return true;
        }

        return false;
    }

    private class ItLanbaoLibApplication {
    }
}

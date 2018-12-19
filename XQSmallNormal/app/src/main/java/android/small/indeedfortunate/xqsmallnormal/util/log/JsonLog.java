package android.small.indeedfortunate.xqsmallnormal.util.log;


import android.util.Log;

public class JsonLog {

    public static void printJson(String tag, String msg, String headString) {
        LUtil.printLine(tag, true);

        int index = 0;
        int maxLength = 3999;
        msg = " ☞ " + msg.replaceAll("\n", "\n║ ☞ ");

        int countOfSub = msg.length() / maxLength;

        Log.i(tag, "║" + headString + "\n");
        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                Log.i(tag, "║" + sub);
                index += maxLength;
            }
            Log.i(tag, "║" + msg.substring(index, msg.length()));
        } else {
            Log.i(tag, "║" + msg);
        }


        LUtil.printLine(tag, false);
    }
}

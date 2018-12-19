package android.small.indeedfortunate.xqsmallnormal;

import android.os.Bundle;
import android.small.indeedfortunate.xqsmallnormal.api.ApiManager;
import android.small.indeedfortunate.xqsmallnormal.model.net.OkHttpUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class Base_InfoActivity extends BaseActivity {
    //private static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base__info);

        initView();
        initData();
    }

    private void initData() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("parentId", "0");
        linkedHashMap.put("level", "1");
        OkHttpUtils.postJson(ApiManager.DICTADDRESS, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Log.e("三级联动省份", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int code, Exception e) {
                Log.e("三级联动身份", e.toString());
            }
        });

    }

    private void initView() {

    }


}

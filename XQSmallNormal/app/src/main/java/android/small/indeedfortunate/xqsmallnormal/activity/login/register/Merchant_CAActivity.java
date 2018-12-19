package android.small.indeedfortunate.xqsmallnormal.activity.login.register;

import android.graphics.Paint;
import android.os.Bundle;
import android.small.indeedfortunate.xqsmallnormal.BaseActivity;
import android.small.indeedfortunate.xqsmallnormal.R;
import android.text.Html;
import android.widget.TextView;

public class Merchant_CAActivity extends BaseActivity {

    private TextView sl1;
    private TextView sl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant__ca);
        initView();
        sl1.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        sl2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    private void initView() {
        sl1 = (TextView) findViewById(R.id.sl1);
        sl2 = (TextView) findViewById(R.id.sl2);
    }
}

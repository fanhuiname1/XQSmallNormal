package android.small.indeedfortunate.xqsmallnormal.activity.login.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.small.indeedfortunate.xqsmallnormal.BaseFragment;
import android.small.indeedfortunate.xqsmallnormal.Base_InfoActivity;
import android.small.indeedfortunate.xqsmallnormal.R;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class Business_ManageFragment extends BaseFragment {

    private LinearLayout mBasicInfo;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_business__manage, null);
        mBasicInfo = view.findViewById(R.id.Basic_Info);
        mBasicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Base_InfoActivity.class));
            }
        });
        return view;
    }

}

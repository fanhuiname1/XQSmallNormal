package android.small.indeedfortunate.xqsmallnormal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.small.indeedfortunate.xqsmallnormal.activity.login.fragment.Business_ManageFragment;
import android.small.indeedfortunate.xqsmallnormal.activity.login.fragment.Home_PageFragment;
import android.small.indeedfortunate.xqsmallnormal.activity.login.fragment.MoreFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String VIEW_PAGER_NUMBER = "VIEW_PAGER_NUMBER";
    private static final String IS_BOSS = "IS_BOSS";
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private ViewPager mViewpager;
    private RadioButton mTvHomePage;
    private RadioButton mTvBusiness;
    private RadioButton mTvMore;
    private int position;
    private RadioGroup mMainTabContainer;
    private BaseFragment mContext;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public static Intent newIntent(Context context, int pageNumber) {
        Intent intent = newIntent(context);
        intent.putExtra(VIEW_PAGER_NUMBER, pageNumber);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

    }


    private void initView() {

        mTvHomePage = (RadioButton) findViewById(R.id.Tv_HomePage);
        mTvBusiness = (RadioButton) findViewById(R.id.Tv_Business);
        mTvMore = (RadioButton) findViewById(R.id.Tv_More);
        mMainTabContainer = (RadioGroup) findViewById(R.id.main_tab_container);

        fragments.add(new Home_PageFragment());
        fragments.add(new Business_ManageFragment());
        fragments.add(new MoreFragment());

    }

    private void initListener(){
        mMainTabContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.Tv_HomePage:
                        position = 0;
                        break;
                    case R.id.Tv_Business:
                        position = 1;
                        break;
                    case R.id.Tv_More:
                        position = 2;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(mContext,baseFragment);
            }
        });
        mTvHomePage.setChecked(true);
    }
    /**
     *
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = (BaseFragment) fragments.get(position);
            return baseFragment;
        }
        return null;
    }
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}

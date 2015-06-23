package com.gaverchou.suidaokou;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaverchou.observablescrollview.ScrollUtils;
import com.gaverchou.suidaokou.adapter.DrawerListAdapter;
import com.gaverchou.suidaokou.model.DrawerSelectItem;
import com.gc.materialdesign.callbak.OnItemClickListener;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-05-27.
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.listView)
    RecyclerView mDrawerSeletItemListv;
    @InjectView(R.id.drawer_layout_touxiang_img)
    View mTouxiangIcon;
    @InjectView(R.id.drawer)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerListAdapter mDrawerAdapter;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
        mDrawerSeletItemListv.setLayoutManager(new LinearLayoutManager(this));
        mDrawerSeletItemListv.setHasFixedSize(true);
        mDrawerAdapter = new DrawerListAdapter(this, getDrawerSeletorData());
        mDrawerSeletItemListv.setAdapter(mDrawerAdapter);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDrawerAdapter.setItemOnclickListener(new DrawerItemClickListener());
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mTouxiangIcon.setOnClickListener(new DrawerViewOnclickListener());
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        setTabSelection(FragmentFactory.HOME_FRAG);
    }

    private Fragment lastFragment;

    @SuppressLint("CommitTransaction")
    public void setTabSelection(int i) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // .setCustomAnimations(R.anim.slide_out_left,
        // R.anim.slide_in_left);
        if (lastFragment == null) {
            lastFragment = FragmentFactory.getFragmentByType(i);
            transaction.add(R.id.main_content_panel, lastFragment).commit();
        } else {
            Fragment to = FragmentFactory.getFragmentByType(i);
            if (to == lastFragment) {
                return;
            } else if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(lastFragment)
                        .add(R.id.main_content_panel, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(lastFragment).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
            lastFragment = to;
        }
    }

    public static ArrayList<DrawerSelectItem> getDrawerSeletorData() {
        ArrayList<DrawerSelectItem> mDrawerSelectItems = new ArrayList<>();
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.school_action));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.consulation_school));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.school_inform));

        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.all_shetuan));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.my_action));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.message_alert));

        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.dingshui));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.superlibray));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.action_settings));
        return mDrawerSelectItems;
    }


    class DrawerViewOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(final View view) {
            int vId = view.getId();
            switch (vId) {
                case R.id.drawer_layout_touxiang_img:
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            int[] startingLocation = new int[2];
                            view.getLocationOnScreen(startingLocation);
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startingLocation[0] += view.getWidth() / 2;
                            intent.putExtra(ProfileActivity.ARG_REVEAL_START_LOCATION, startingLocation);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    });
                    break;
            }
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    class DrawerItemClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(View view, int pos) {
            switch (pos) {
                case 0:
                    mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mBaseColor));
                    setTabSelection(FragmentFactory.HOME_FRAG);
                    HomeFragment.instance().getmViewPager().setCurrentItem(0);
                    break;
                case 1:
                    mToolbarView.setBackgroundColor(mBaseColor);
                    setTabSelection(FragmentFactory.HOME_FRAG);
                    HomeFragment.instance().getmViewPager().setCurrentItem(1);
                    break;
                case 2:
                    mToolbarView.setBackgroundColor(mBaseColor);
                    setTabSelection(FragmentFactory.HOME_FRAG);
                    HomeFragment.instance().getmViewPager().setCurrentItem(2);
                    break;
                case 3:
                    mToolbarView.setBackgroundColor(mBaseColor);
                    mToolbar.setTitle(R.string.all_shetuan);
                    setTabSelection(FragmentFactory.ORG_FRAG);
                    break;
                case 4:
                    mToolbarView.setBackgroundColor(mBaseColor);
                    mToolbar.setTitle(R.string.my_action);
                    mToolbar.setTitle(R.string.my_action);
                    setTabSelection(FragmentFactory.MY_ACTION_FRAG);
                    break;
                case 5:
                    mToolbarView.setBackgroundColor(mBaseColor);
                    mToolbar.setTitle(R.string.message_alert);
                    setTabSelection(FragmentFactory.MSG_FRAG);
                    break;
                case 6:
                    mToolbarView.setBackgroundColor(mBaseColor);
                    mToolbar.setTitle(R.string.dingshui);
                    setTabSelection(FragmentFactory.DINGSHUI_FRAG);
                    break;
                case 7:
                    mToolbarView.setBackgroundColor(mBaseColor);
                    mToolbar.setTitle(R.string.superlibray);
                    setTabSelection(FragmentFactory.SUPERLIBRARY_FRAG);
                    break;
                default:
//                    mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mBaseColor));
//                    setTabSelection(FragmentFactory.GROUP_INFO_FRAG);
                    Intent intent = new Intent(MainActivity.this,GroupActivity.class);
                    startActivity(intent);
                    break;
            }
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}

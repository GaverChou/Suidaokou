package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.gaverchou.observablescrollview.ScrollUtils;
import com.gaverchou.suidaokou.adapter.DrawerListAdapter;
import com.gaverchou.suidaokou.model.DrawerSelectItem;

import java.util.ArrayList;

/**
 * Created by GaverChou on 2015-05-27.
 */
public class MainActivity extends BaseActivity {
    private View mToolbarView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private ListView mDrawerSeletItemListv;
    private DrawerListAdapter mDrawerAdapter;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        transaction.replace(R.id.main_content_panel,
                new HomeFragment()).commit();
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        mToolbarView = findViewById(R.id.main_toolbar);
        mDrawerSeletItemListv = (ListView) findViewById(R.id.main_drawer_view).findViewById(R.id.listView);
        mDrawerAdapter = new DrawerListAdapter(getDrawerSeletorData());
        mDrawerSeletItemListv.setAdapter(mDrawerAdapter);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.primary)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public static ArrayList<DrawerSelectItem> getDrawerSeletorData() {
        ArrayList<DrawerSelectItem> mDrawerSelectItems = new ArrayList<>();
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.school_action));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.my_action));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.all_shetuan));
        mDrawerSelectItems.add(new DrawerSelectItem(R.mipmap.me, R.string.message_alert));
        mDrawerSelectItems.add(new DrawerSelectItem(R.string.dingshui));
        mDrawerSelectItems.add(new DrawerSelectItem(R.string.superlibray));
        mDrawerSelectItems.add(new DrawerSelectItem(R.string.action_settings));
        return mDrawerSelectItems;
    }

    public View getmToolbarView() {
        return mToolbarView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        onScrollChanged(mListView.getCurrentScrollY(), false, false);
//    }

}

package com.gaverchou.suidaokou;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import com.gaverchou.observablescrollview.ScrollUtils;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * Created by GaverChou on 2015-05-27.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Optional
    @InjectView(R.id.main_toolbar)
    protected View mToolbarView;
    protected Toolbar mToolbar;
    protected int mBaseColor;

    @Optional
    @InjectView(R.id.actinfo_input_ll)
    protected View mInputPanel;
    @Optional
    @InjectView(R.id.actinfo_float_menu)
    FloatingActionsMenu mFloatBtn;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
        init();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    protected void init() {
        initData();
        initView();
        initEvent();
    }

    protected void initEvent() {

    }

    protected void initData() {
        mBaseColor = getResources().getColor(R.color.primary);
    }

    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
//        mToolbarView = findViewById(R.id.main_toolbar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mBaseColor));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public View getmToolbarView() {
        return mToolbarView;
    }

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }


    protected void showFab() {
        if (!mFabIsShown) {
            mInputPanel.setVisibility(View.VISIBLE);
            offsetInputPanel = mInputPanel.getHeight();
            List<Animator> profileAnimators = new ArrayList<>();
            if (mFloatBtn!=null){
                Animator floatAnim = ObjectAnimator.ofFloat(mFloatBtn, "Y", ViewHelper.getY(mFloatBtn), ViewHelper.getY(mFloatBtn) - offsetInputPanel).setDuration(200);
                profileAnimators.add(floatAnim);
            }
            Animator inputPanelAnimX = ObjectAnimator.ofFloat(mInputPanel, "ScaleX", 0, 1).setDuration(200);
            Animator inputPanelAnimY = ObjectAnimator.ofFloat(mInputPanel, "ScaleY", 0, 1).setDuration(200);
            profileAnimators.add(inputPanelAnimX);
            profileAnimators.add(inputPanelAnimY);

            mShowFabAnimatorSet = new AnimatorSet();
            mShowFabAnimatorSet.playTogether(profileAnimators);
            mShowFabAnimatorSet.start();
            mFabIsShown = true;
        }
    }

    public final static int ANIMATOR_DELAY = 200;
    protected boolean mFabIsShown;
    public int offsetInputPanel;
    protected AnimatorSet mShowFabAnimatorSet;
    protected AnimatorSet mHideFabAnimatorSet;

    protected void hideFab() {
        if (mFabIsShown) {
            mInputPanel.setVisibility(View.INVISIBLE);
            List<Animator> profileAnimators = new ArrayList<>();
            if (mFloatBtn!=null) {
                Animator floatAnim = ObjectAnimator.ofFloat(mFloatBtn, "Y", ViewHelper.getY(mFloatBtn), ViewHelper.getY(mFloatBtn) + offsetInputPanel).setDuration(200);
                profileAnimators.add(floatAnim);
            }
            Animator inputPanelAnimX = ObjectAnimator.ofFloat(mInputPanel, "ScaleX", 0).setDuration(200);
            Animator inputPanelAnimY = ObjectAnimator.ofFloat(mInputPanel, "ScaleY", 0).setDuration(200);
            profileAnimators.add(inputPanelAnimX);
            profileAnimators.add(inputPanelAnimY);

            mHideFabAnimatorSet = new AnimatorSet();
            mHideFabAnimatorSet.playTogether(profileAnimators);
            mHideFabAnimatorSet.start();
            mFabIsShown = false;
        }
    }
}

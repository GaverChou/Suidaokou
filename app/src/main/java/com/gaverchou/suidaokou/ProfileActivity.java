package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.gaverchou.suidaokou.util.Utility;
import com.gaverchou.suidaokou.widget.RevealBackgroundView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class ProfileActivity extends BaseActivity implements RevealBackgroundView.OnStateChangeListener {

    @InjectView(R.id.profile_info_ll)
    View mInfoView;
    @InjectView(R.id.profile_moto_ll)
    View mMotoView;
    @InjectView(R.id.profile_header)
    View mHeadView;
    @InjectView(R.id.bg)
    View mBgView;
    @InjectView(R.id.activity_start_mask)
    RevealBackgroundView vRevealBackground;
    @InjectView(R.id.profile_headerview_userimg)
    View mHeadViewUserImg;
    @InjectView(R.id.profile_headerview_username_tv)
    TextView mHeadViewUserNameTv;
    @InjectView(R.id.profile_headerview_school_tv)
    TextView mHeadViewSchoolTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        setupRevealBackground(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar.setTitle(R.string.profile);
    }

    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(ARG_REVEAL_START_LOCATION);
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
        }
    }

    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    public final static int ANIMATOR_DELAY = 200;
    private AnimatorSet mStartProfileAnimatorSet;

    private void playStartAnimatorSet() {
        mToolbar.setTranslationY(-mToolbar.getHeight());
        mHeadView.setTranslationY(-mHeadView.getHeight());
        mMotoView.setTranslationX(-Utility.getScreenWidth(this));
        mInfoView.setTranslationX(-Utility.getScreenWidth(this));
//        mHeadViewUserImg.setTranslationY(-mHeadView.getHeight());
        mHeadViewUserImg.setAlpha(0);
        mHeadViewUserNameTv.setAlpha(0);
        mHeadViewSchoolTv.setAlpha(0);
        if (mStartProfileAnimatorSet == null) {
            Animator mHeadViewAnimator = ObjectAnimator.ofFloat(mHeadView, "TranslationY", 0);
            mHeadViewAnimator.setInterpolator(INTERPOLATOR);
            Animator mMotoViewAnimator = ObjectAnimator.ofFloat(mMotoView, "TranslationX", 0);
            mMotoViewAnimator.setInterpolator(INTERPOLATOR);
            Animator mInfoViewAnimator = ObjectAnimator.ofFloat(mInfoView, "TranslationX", 0);
            mInfoViewAnimator.setStartDelay(ANIMATOR_DELAY);
            mInfoViewAnimator.setInterpolator(INTERPOLATOR);

            Animator mToolBarAnimator = ObjectAnimator.ofFloat(mToolbar, "TranslationY", 0);
            mToolBarAnimator.setStartDelay(ANIMATOR_DELAY * 2);
            mToolBarAnimator.setInterpolator(INTERPOLATOR);

            Animator mHeadViewUserImgAnimator = ObjectAnimator.ofFloat(mHeadViewUserImg, "Alpha", 1);
            mHeadViewUserImgAnimator.setDuration(1000);
            mHeadViewUserImgAnimator.setStartDelay(ANIMATOR_DELAY * 2);
            mHeadViewUserImgAnimator.setInterpolator(new BounceInterpolator());
            Animator mHeadViewUserNameTvAnimator = ObjectAnimator.ofFloat(mHeadViewUserNameTv, "Alpha", 1);
            mHeadViewUserNameTvAnimator.setDuration(1000);
            mHeadViewUserNameTvAnimator.setStartDelay(ANIMATOR_DELAY * 2);
            mHeadViewUserNameTvAnimator.setInterpolator(INTERPOLATOR);
            Animator mHeadViewSchoolTvAnimator = ObjectAnimator.ofFloat(mHeadViewSchoolTv, "Alpha", 1);
            mHeadViewSchoolTvAnimator.setDuration(1000);
            mHeadViewSchoolTvAnimator.setStartDelay(ANIMATOR_DELAY * 2);
            mHeadViewSchoolTvAnimator.setInterpolator(INTERPOLATOR);

            List<Animator> profileAnimators = new ArrayList<>();
            profileAnimators.add(mHeadViewAnimator);
            profileAnimators.add(mMotoViewAnimator);
            profileAnimators.add(mInfoViewAnimator);
            profileAnimators.add(mToolBarAnimator);
            profileAnimators.add(mHeadViewUserImgAnimator);
            profileAnimators.add(mHeadViewUserNameTvAnimator);
            profileAnimators.add(mHeadViewSchoolTvAnimator);

            mStartProfileAnimatorSet = new AnimatorSet();
            mStartProfileAnimatorSet.playTogether(profileAnimators);
        }
        mStartProfileAnimatorSet.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
            vRevealBackground.setVisibility(View.INVISIBLE);
            mBgView.setVisibility(View.VISIBLE);
            mInfoView.setVisibility(View.VISIBLE);
            mMotoView.setVisibility(View.VISIBLE);
            mHeadView.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.VISIBLE);
            playStartAnimatorSet();
        } else {
            mInfoView.setVisibility(View.INVISIBLE);
            mMotoView.setVisibility(View.INVISIBLE);
            mHeadView.setVisibility(View.INVISIBLE);
            mToolbar.setVisibility(View.INVISIBLE);
            mBgView.setVisibility(View.INVISIBLE);
        }
    }
}

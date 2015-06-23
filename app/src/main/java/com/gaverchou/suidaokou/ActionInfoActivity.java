package com.gaverchou.suidaokou;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaverchou.observablescrollview.ObservableScrollViewCallbacks;
import com.gaverchou.observablescrollview.ScrollState;
import com.gaverchou.observablescrollview.ScrollUtils;
import com.gaverchou.suidaokou.adapter.ActInfoCommentListAdapter;
import com.gaverchou.suidaokou.adapter.WantGoGridAdapter;
import com.gaverchou.suidaokou.network.LoadImageFromUrl;
import com.gaverchou.suidaokou.model.ActionItem;
import com.gaverchou.suidaokou.model.WantgoItem;
import com.gaverchou.suidaokou.widget.ImageRefreshListView;
import com.gaverchou.suidaokou.widget.MyDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by mrgaver on 15-6-4.
 */
public class ActionInfoActivity extends BaseActivity implements ObservableScrollViewCallbacks {
    GridView mGridView = null;
    @InjectView(R.id.fragment_actinfo_comment_lv)
    ImageRefreshListView mListView = null;

    LinearLayout headView;
    View headViewImg;
    View headViewPanel;
    View headViewWanggoLL;
    TextView headTitNameTv;
    TextView headTimeTv;
    TextView headNameTv;
    TextView headSendTimeTv;
    WebView headActContentWebView;

    private int mParallaxImageHeight;


    @InjectView(R.id.mask_view)
    View mMaskView;
    @InjectView(R.id.action_bc)
    FloatingActionButton mWantGoBtn;
    @InjectView(R.id.activity_start_mask)
    View mBgView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_action_info);
    }

    static int[] imgId = new int[]{R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4,
            R.mipmap.img5, R.mipmap.img6, R.mipmap.img7, R.mipmap.img8, R.mipmap.img9, R.mipmap.img10};

    public static ArrayList<WantgoItem> getDummyData() {
        ArrayList<WantgoItem> items = new ArrayList<WantgoItem>();
        for (int i = 0; i < imgId.length; i++) {
            WantgoItem actionItem = new WantgoItem();
            actionItem.imgId = imgId[i];
            items.add(actionItem);
        }
        return items;
    }

    int[] startingLocation;
    ActionItem actionItem;

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        startingLocation = intent.getIntArrayExtra(ProfileActivity.ARG_REVEAL_START_LOCATION);
        actionItem = (ActionItem) intent.getSerializableExtra(AllActionFragment.ACTION_ITEM);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_big_image_height);
        headView = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.fragment_action_info_header,
                null);
        headView.setVisibility(View.INVISIBLE);
        headView.post(new Runnable() {
            @Override
            public void run() {
                playHeadViewAnimSet();
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar.setTitle(null);
        mGridView = (GridView) headView.findViewById(R.id.fragment_actinfo_wantgo_layout);
        mGridView.setAdapter(new WantGoGridAdapter(getDummyData()));

        headViewImg = headView.findViewById(R.id.path_headimage);
        LoadImageFromUrl.initLoader(this);
        LoadImageFromUrl.loadIntoNetworkImageView((com.android.volley.toolbox.NetworkImageView) headViewImg, actionItem.mPicUrl, R.mipmap.poster);
        headViewPanel = headView.findViewById(R.id.headview_divide_panel);
        headViewWanggoLL = headView.findViewById(R.id.wanggo_ll);

        headTitNameTv = (TextView) headView.findViewById(R.id.action_info_header_action_nametit_tv);
        headTitNameTv.setText(actionItem.mActionName);
        headTimeTv = (TextView) headView.findViewById(R.id.action_info_header_action_time_tv);
        headTimeTv.setText(actionItem.mMouth + " " + actionItem.mDay + " " + actionItem.mTime + " " + actionItem.mAddr);
        headNameTv = (TextView) headView.findViewById(R.id.action_info_header_action_name_tv);
        headNameTv.setText(actionItem.mActionName);
        headSendTimeTv = (TextView) headView.findViewById(R.id.action_info_header_action_sendtime_tv);
        headSendTimeTv.setText(actionItem.mSendTime);
        headActContentWebView = (WebView) headView.findViewById(R.id.fragment_actinfo_content_webview);
        headActContentWebView.setBackgroundColor(getResources().getColor(R.color.white));
        headActContentWebView.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        headActContentWebView.loadData(actionItem.mHtmlContent, "text/html; charset=UTF-8", null);

        mListView.setHeadView(headViewImg);
        mListView.setBgView(headViewImg);
        headViewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                headViewImg.setLayoutParams(new LinearLayout.LayoutParams(headViewImg
//                        .getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT));
            }
        });
        mListView.addHeaderView(headView);
        mListView.getHeadView();
        mListView.setAdapter(new ActInfoCommentListAdapter());
        mListView.setScrollViewCallbacks(this);
        mListView.setEnabled(false);

        ViewHelper.setScaleX(mInputPanel, 0);
        ViewHelper.setScaleY(mInputPanel, 0);
        mInputPanel.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        headView.removeView(headActContentWebView);
        headActContentWebView.removeAllViews();
        headActContentWebView.destroy();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mFloatBtn.getmAddButton().setOnClickListener(new ToggleListener());
        mMaskView.setOnClickListener(new ToggleListener());

        mWantGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog dialog = new MyDialog(ActionInfoActivity.this, "活动提醒");
                dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(getActivity(),
//                                "Click accept button", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.addCancelButton("取消", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(getActivity(),
//                                "Click cancel button", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();
            }
        });
        headViewWanggoLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActionInfoActivity.this, WangoDetail.class);
                startActivity(intent);
            }
        });
    }

    class ToggleListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mMaskView.setVisibility(mMaskView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            mFloatBtn.toggle();
        }
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.primary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        if (alpha >= 1) {
            showFab();
        } else {
            hideFab();
        }
        // Translate list background
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    private AnimatorSet mHeadViewAnimSet;
    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();

    protected void playHeadViewAnimSet() {
        mToolbar.setTranslationY(-mToolbar.getHeight());
        headViewImg.setTranslationY(-headViewImg.getHeight());
        headViewPanel.setTranslationY(headViewPanel.getHeight());
        if (mHeadViewAnimSet == null) {

            Animator mHeadViewImgAnimator = ObjectAnimator.ofFloat(headViewImg, "TranslationY", 0);
            mHeadViewImgAnimator.setDuration(500);
            mHeadViewImgAnimator.setInterpolator(INTERPOLATOR);

            Animator mHeadViewPanelAnimator = ObjectAnimator.ofFloat(headViewPanel, "TranslationY", 0);
            mHeadViewPanelAnimator.setDuration(500);
            mHeadViewPanelAnimator.setInterpolator(INTERPOLATOR);

            Animator mToolBarAnimator = ObjectAnimator.ofFloat(mToolbar, "TranslationY", 0);
            mToolBarAnimator.setInterpolator(INTERPOLATOR);

            List<Animator> profileAnimators = new ArrayList<>();
            profileAnimators.add(mHeadViewImgAnimator);
            profileAnimators.add(mHeadViewPanelAnimator);
            profileAnimators.add(mToolBarAnimator);
            mHeadViewAnimSet = new AnimatorSet();
            mHeadViewAnimSet.playTogether(profileAnimators);
            mHeadViewAnimSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    headView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mListView.setEnabled(true);
                    mBgView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        mHeadViewAnimSet.start();
    }

}

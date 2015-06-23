package com.gaverchou.suidaokou;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gaverchou.observablescrollview.ObservableListView;
import com.gaverchou.observablescrollview.ObservableScrollViewCallbacks;
import com.gaverchou.observablescrollview.ScrollState;
import com.gaverchou.observablescrollview.ScrollUtils;
import com.gaverchou.suidaokou.adapter.ActInfoCommentListAdapter;
import com.gaverchou.suidaokou.model.ActionItem;
import com.gaverchou.suidaokou.model.ConsulationItem;
import com.nineoldandroids.view.ViewHelper;

import butterknife.InjectView;

/**
 * Created by mrgaver on 15-6-4.
 */
public class ConsulationDetailActivity extends BaseActivity implements ObservableScrollViewCallbacks  {
    @InjectView(R.id.inform_detail_listview)
    ObservableListView mListView;

    public final static String CONSULATION_ITEM = "CosulationItem";
    LinearLayout headView;

    WebView headActContentWebView;

    @InjectView(R.id.actinfo_input_ll)
    View mInputPanel;
    ConsulationItem consulationItem;
    ImageView mConsulationDetailBg;
    TextView mConsulationNameTv;
    TextView mConsulationZanTv;
    TextView mConsulationGroupNameTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform_detail);
    }

    @Override
    protected void initData() {
        super.initData();
        headView = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.consulation_detail_header,
                null);
        Intent intent = getIntent();
        consulationItem = (ConsulationItem) intent.getSerializableExtra(CONSULATION_ITEM);
        Log.d("ccccc",consulationItem.consulationName);
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar.setTitle(null);
        headActContentWebView = (WebView) headView.findViewById(R.id.fragment_actinfo_content_webview);
        mConsulationDetailBg = (ImageView)headView.findViewById(R.id.consulation_bg_img);
        mConsulationDetailBg.setBackgroundResource(consulationItem.consulationImgId);
        mConsulationNameTv = (TextView)headView.findViewById(R.id.consulation_detail_name_tv);
        mConsulationNameTv.setText(consulationItem.consulationName);
        mConsulationZanTv = (TextView)headView.findViewById(R.id.consulation_detail_zan_tv);
        mConsulationGroupNameTv = (TextView)headView.findViewById(R.id.consulation_group_name_tv);
        mConsulationGroupNameTv.setText(consulationItem.consulationLoc);
        headActContentWebView.setBackgroundColor(0xfff2f2f2); // 设置背景色
        headActContentWebView.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        headActContentWebView.loadData("<img alt=\"pic\" class=\"pic\" data-bind=\"img:src;sty:style\" title=\"点击查看源网页\" src=\"http://d.hiphotos.baidu.com/image/pic/item/d53f8794a4c27d1ea15953c419d5ad6eddc438b6.jpg\" style=\"width: 100%; height: 257px; top: 0px; left: 190.4px;\">", "text/html; charset=UTF-8", null);

        mListView.addHeaderView(headView);
        mListView.setAdapter(new ActInfoCommentListAdapter());
        mListView.setScrollViewCallbacks(this);

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
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.primary);
        float alpha = Math.min(1, (float) scrollY / 200);
        mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        if (alpha >= 1) {
            showFab();
        } else {
            hideFab();
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    private boolean mFabIsShown;

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}

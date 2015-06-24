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
        String test = "<table border=\"0\" class=\"winstyle53824\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "\n" +
                "      <tbody><tr><td class=\"titlestyle53824\" align=\"center\">\n" +
                "关于开展建校50周年校庆“广外故事”有奖征文活动的通知\n" +
                "      </td></tr>\n" +
                "      <tr height=\"30\"><td align=\"center\">\n" +
                "            <span class=\"timestyle53824\">\n" +
                "                 2015-06-18</span>\n" +
                "            <span class=\"authorstyle53824\">&nbsp;</span>\n" +
                "        </td></tr>\n" +
                "        <tr><td align=\"right\">\n" +
                "        <span>\n" +
                "\n" +
                "        </span>\n" +
                "        </td></tr>\n" +
                "\n" +
                "      <tr><td class=\"contentstyle53824\">\n" +
                "        <div class=\"c53824_content\" id=\"vsb_newscontent\"><div id=\"vsb_content\">\n" +
                "           <div class=\"Section1\" style=\"vsb_tmp: 21px\">\n" +
                " <p class=\"MsoNormal\" style=\"text-align: center\"><span style=\"color: rgb(76, 76, 76);font-family: 黑体;font-size: 24px\"></span> &nbsp;</p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;word-break: break-all\"><span lang=\"EN-US\" style=\"font-family: calibri;font-size: 21px\"></span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">全校师生、各地校友、社会各界人士：</span><span lang=\"EN-US\" style=\"font-family: verdana;font-size: 12px\"></span> &nbsp;&nbsp;</p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">50 </span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">年岁月流转，不变的是我们对母校殷殷的牵挂；</span><span lang=\"EN-US\" style=\"font-size: 21px\">50</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">年时光荏苒，留下的是我们对母校浓浓的情意。为反映广外</span><span lang=\"EN-US\" style=\"font-size: 21px\">50</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">年发展历程，记录一代代广外人励精图治、拼搏进取的感人故事，抒写一段段广外师生与母校之间的真挚情感和美好回忆。在广外迎来了建校</span><span lang=\"EN-US\" style=\"font-size: 21px\">50</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">周年之际，学校决定举办建校</span><span lang=\"EN-US\" style=\"font-size: 21px\">50</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">周年校庆“广外故事”有奖征文活动。现向全校师生、全体离退休教职工、各地校友以及社会各界人士征集稿件，具体要求如下：</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><strong><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">一、征文内容</span></strong></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">1</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">记录在广外经历过的有意义的、感人的事情；</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">2</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">记录发生在广外校园（包括前身广州外国语学院、广州外贸学院、广东财经职业技术学院）一些鲜为人知的秘闻典故；</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">3</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">回忆与广外名师名人交往的趣闻轶事；</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">4</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">回忆亲历的广外发展史上的重大改革、重大事件及重大活动；</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">5</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">介绍广外校园人文景观的历史变迁、文化底蕴等。</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><strong><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">二、征文要求</span></strong></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">1</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">内容真实可靠，情感真挚。</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">2</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">文字简练，可读性强。每篇字数原则上要求在</span><span lang=\"EN-US\" style=\"font-size: 21px\">1000</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">至</span><span lang=\"EN-US\" style=\"font-size: 21px\">3</span><span lang=\"EN-US\" style=\"font-size: 21px\">000</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">字之间。</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">3</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">体裁以记叙文为主。</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">4. </span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">最好提供相关历史照片。</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><strong><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">三、投稿方式和截稿日期</span></strong></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">1</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\">.</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">将稿件以电子邮件方式发送到电子邮箱：</span><span lang=\"EN-US\" style=\"font-size: 21px\">gwxb117@126.com</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">，标题请注明“校庆征文”字样；也可将纸质作品、历史相片等交（寄）到广州白云大道北</span><span lang=\"EN-US\" style=\"font-size: 21px\">2</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">号广东外语外贸大学第三教学楼</span><span lang=\"EN-US\" style=\"font-size: 21px\">417</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">校报编辑部（联系人：梁玲华办公电话：</span><span lang=\"EN-US\" style=\"font-size: 21px\">36207017</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\"></span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">邮编：</span><span lang=\"EN-US\" style=\"font-size: 21px\">510420</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">）。</span> &nbsp;&nbsp;</p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">2.</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\"></span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">稿件须注明真实姓名、工作或学习单位、手机等有效的联系方式。</span> &nbsp;&nbsp;</p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">3.</span><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\"></span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">也可向校庆宣传组提供口述、历史素材等，由宣传组派出记者进行笔录或采写。</span> &nbsp;&nbsp;</p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 21px;margin-left: 21px;word-break: break-all\"><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">截稿日期：</span><span lang=\"EN-US\" style=\"font-size: 21px\">2015</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">年</span><span lang=\"EN-US\" style=\"font-size: 21px\">9</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">月</span><span lang=\"EN-US\" style=\"font-size: 21px\">15</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">日</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;margin-left: 21px;word-break: break-all\"><strong><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">四、评奖办法</span></strong></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;margin-left: 21px;word-break: break-all\"><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">截稿后，组织专家对所有来稿进行评审，评出一等奖、二等奖、三等奖若干名，给获奖作品颁发证书和奖金。</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: left;line-height: 39px;text-indent: 43px;word-break: break-all\"><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">学校将挑选优秀作品在校园网、校庆专网、校报专题栏目以及校庆特刊发表。</span></p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: right;line-height: 39px;margin-right: 53px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\"></span> &nbsp;</p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: right;line-height: 39px;margin-right: 10px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-family: 仿宋_gb2312;font-size: 21px\"></span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">广东外语外贸大学校庆宣传组</span> &nbsp;</p>\n" +
                " <p class=\"MsoNormal\" style=\"text-align: right;line-height: 39px;margin-right: 53px;word-break: break-all\"><span lang=\"EN-US\" style=\"font-size: 21px\">2015</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">年</span><span lang=\"EN-US\" style=\"font-size: 21px\">3</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">月</span><span lang=\"EN-US\" style=\"font-size: 21px\">24</span><span style=\"font-family: 仿宋_gb2312;font-size: 21px\">日</span></p>\n" +
                "</div>\n" +
                "</div></div>\n" +
                "          \n" +
                "           \n" +
                "           \n" +
                "        </td></tr>\n" +
                "        \n" +
                "        <tr><td class=\"pagestyle53824\" align=\"left\">\n" +
                "        </td></tr>\n" +
                "        <tr><td><div id=\"div_vote_id\"></div></td></tr>\n" +
                "    <tr><td align=\"left\"><span>\n" +
                "             \n" +
                "             \n" +
                "      </span></td></tr>\n" +
                "      \n" +
                "      <tr>\n" +
                "         <td align=\"center\">\n" +
                "            \n" +
                "         </td>\n" +
                "      </tr>\n" +
                "\n" +
                "</tbody></table>";
        headActContentWebView.loadData(test, "text/html; charset=UTF-8", null);

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

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}

package com.gaverchou.suidaokou.model;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class ZanCommentItem {
    public String mTitleStr;
    public String mTimeStr;
    public String mSubTitleStr;
    public String mCommentStr;

    public ZanCommentItem() {
    }

    public ZanCommentItem(String mCommentStr, String mSubTitleStr, String mTimeStr, String mTitleStr) {
        this.mCommentStr = mCommentStr;
        this.mSubTitleStr = mSubTitleStr;
        this.mTimeStr = mTimeStr;
        this.mTitleStr = mTitleStr;
    }
}

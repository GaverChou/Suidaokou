package com.gaverchou.suidaokou.model;

import java.io.Serializable;

/**
 * Created by GaverChou on 2015-05-28.
 */
public class ActionItem implements Serializable{
    public String mActionName;
    public String mCollegaName;
    public String mAddr;
    public String mSendTime;
    public String mDay;
    public String mMouth;
    public String mTime;
    public String mPicUrl;
    public String mHtmlContent;

    public ActionItem() {
        mSendTime = "Send yesterday";
        mDay = "24";
        mMouth="Oct.";
        mTime = "18:45";
    }
}

package com.gaverchou.suidaokou.model;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class OrgItem {
    public String mImgUrl;
    public String mOrgName;
    public String mDescrip;

    public OrgItem() {
    }

    public OrgItem(String mDescrip, String mOrgName) {
        this.mDescrip = mDescrip;
        this.mOrgName = mOrgName;
    }
}

package com.gaverchou.suidaokou.model;

/**
 * Created by mrgaver on 15-5-28.
 */
public class DrawerSelectItem {
    public int mIconId;
    public int mTitleId;
    public int mType;
    public final static int NO_ICON = 0x00;
    public final static int HAS_ICON = 0x01;

    public DrawerSelectItem(int mIconId, int mTitleId) {
        this.mIconId = mIconId;
        this.mTitleId = mTitleId;
        this.mType = HAS_ICON;
    }

    public DrawerSelectItem( int mTitleId) {
        this.mIconId = mIconId;
        this.mTitleId = mTitleId;
        this.mType = NO_ICON;
    }
}

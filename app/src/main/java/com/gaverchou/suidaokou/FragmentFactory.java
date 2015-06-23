package com.gaverchou.suidaokou;

import android.support.v4.app.Fragment;

/**
 * Created by mrgaver on 15-5-29.
 */
public class FragmentFactory {
    public final static int HOME_FRAG = 0x00;
    public final static int DINGSHUI_FRAG = 0x01;
    public final static int ORG_FRAG = 0x02;
    public final static int MY_ACTION_FRAG = 0x03;
    public final static int MSG_FRAG = 0x04;
    public final static int SUPERLIBRARY_FRAG = 0x05;

    public static Fragment getFragmentByType(int type) {
        Fragment fragment = null;
        switch (type) {
            case HOME_FRAG:
                fragment = HomeFragment.instance();
                break;
            case DINGSHUI_FRAG:
                fragment = DingshuiFragment.instance();
                break;
            case ORG_FRAG:
                fragment = OrgFragment.instance();
                break;
            case MY_ACTION_FRAG:
                fragment = MyActionFragment.instance();
                break;
            case SUPERLIBRARY_FRAG:
                fragment = LibraryFragment.instance();
                break;
            case MSG_FRAG:
                fragment = MessageFragment.instance();
                break;
        }
        return fragment;
    }
}

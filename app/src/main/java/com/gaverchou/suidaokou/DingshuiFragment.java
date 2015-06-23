package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.Switch;

import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-06-18.
 */
public class DingshuiFragment extends BaseFragment<MainActivity> {
    @InjectView(R.id.switchView)
    Switch mSwitch;
    @InjectView(R.id.dingshui_yitong_tv)
    TextView mYitongTv;
    @InjectView(R.id.dingshui_twotong_tv)
    TextView mLiangtongTv;
    @InjectView(R.id.dingshui_img)
    ImageView mWaterImg;
    @InjectView(R.id.dingshui_wt_switch)
    RelativeLayout mWtSwitchRl;

    public static DingshuiFragment mInstance = null;

    public synchronized static DingshuiFragment instance() {
        if (mInstance == null) {
            mInstance = new DingshuiFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.createView(inflater, container, savedInstanceState, R.layout.fragment_dingshui);
    }
    boolean isCheck;
    @Override
    protected void initEvent() {
        super.initEvent();
        mWtSwitchRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCheck= !isCheck;
                mSwitch.setChecked(isCheck);
                switchCheck(isCheck);
            }
        });
        mSwitch.setOncheckListener(new Switch.OnCheckListener() {
            @Override
            public void onCheck(Switch view, boolean check) {
                switchCheck(isCheck);
            }
        });
        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchCheck(isCheck);
            }
        });

    }

    public void switchCheck(boolean isCheck){
        if (isCheck){
            mYitongTv.setTextColor(getResources().getColor(R.color.gray));
            mLiangtongTv.setTextColor(getResources().getColor(R.color.black));
            mWaterImg.setImageResource(R.mipmap.water23);
        }else {
            mLiangtongTv.setTextColor(getResources().getColor(R.color.gray));
            mYitongTv.setTextColor(getResources().getColor(R.color.black));
            mWaterImg.setImageResource(R.mipmap.water1);
        }
    }
}

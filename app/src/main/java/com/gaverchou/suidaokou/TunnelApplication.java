package com.gaverchou.suidaokou;

import android.app.Application;
import android.content.Context;

import com.gaverchou.suidaokou.network.VolleyUtil;

/**
 * Created by mrgaver on 15-6-11.
 */
public class TunnelApplication extends Application {
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initialize();

    }

    private void initialize() {
        initRequestQueue();
    }

    private void initRequestQueue() {
        VolleyUtil.initialize(mContext);
    }
}

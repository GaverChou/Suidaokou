package com.gaverchou.suidaokou.network;

import android.content.Context;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by mrgaver on 15-6-11.
 */
public abstract class Controler {
    public final static String BASE_URL = "http://120.24.78.54/suidaokou/index.php/Home/Activity/activityInterface";
    public final static String IMG_BASE_URL = "http://120.24.78.54/suidaokou/";

    protected static ImageLoader imageLoader;
    public synchronized static void initLoader(Context context){
        if (imageLoader==null) {
            imageLoader = new ImageLoader(VolleyUtil.getRequestQueue(), new BitmapCache());
        }
    }
}

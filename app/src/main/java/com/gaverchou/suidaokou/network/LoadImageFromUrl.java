package com.gaverchou.suidaokou.network;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by mrgaver on 15-6-11.
 */
public class LoadImageFromUrl extends Controler {
    public static void loadIntoNetworkImageView(NetworkImageView networkImageView, String imgUrl, int errorImgResId) {
        networkImageView.setImageUrl(imgUrl, imageLoader);
        networkImageView.setDefaultImageResId(errorImgResId);
        networkImageView.setErrorImageResId(errorImgResId);
    }

    public static void loadNetworkImage(String imgUrl, Response.Listener listener) {
        ImageRequest irequest = new ImageRequest(
                imgUrl, listener, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });
        VolleyUtil.getRequestQueue().add(irequest);
    }
}

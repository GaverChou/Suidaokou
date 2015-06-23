package com.gaverchou.suidaokou.api;

import com.android.volley.Request;
import com.gaverchou.suidaokou.model.ActionResultList;
import com.gaverchou.suidaokou.network.PostJsonObjectRequest;
import com.gaverchou.suidaokou.network.ResponseListener;
import com.gaverchou.suidaokou.network.VolleyUtil;
import com.google.gson.reflect.TypeToken;

/**
 * Created by mrgaver on 15-6-12.
 */
public class ActionApi extends BaseApi {

    public static void postObjectSearchApi(int from, int to, ResponseListener listener) {
        String paraStr = "{\"from\":\"" + from + "\",\"cnt\":\"" + to + "\",\"type\":2000}";
        Request request = new PostJsonObjectRequest(BASE_URL, paraStr, new TypeToken<ActionResultList>() {
        }.getType(), listener);
        VolleyUtil.getRequestQueue().add(request);
    }
}

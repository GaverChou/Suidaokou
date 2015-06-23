package com.gaverchou.suidaokou.model;

import java.util.List;

/**
 * Created by mrgaver on 15-6-12.
 */
public class ActionResultList {
    private String type;
    private String re;
    private List<ActionResult> activity;

    public List<ActionResult> getActivity() {
        return activity;
    }

    public void setActivity(List<ActionResult> activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }
}

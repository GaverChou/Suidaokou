package com.gaverchou.suidaokou.model;

import com.gaverchou.suidaokou.network.Controler;
import com.gaverchou.suidaokou.util.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mrgaver on 15-6-12.
 */
public class ActionResult {
    private String id;
    private String activity_location;
    private String activity_start_time;
    private String activity_remark;
    private String activity_content;
    private String association_name;
    private String activity_name;
    private String activity_pic_url;
    private String activity_send_date;
    private String activity_end_time;
    private String activity_tailor_br;
    private String activity_tailor_tl;
    private String activity_nav_color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivity_location() {
        return activity_location;
    }

    public void setActivity_location(String activity_location) {
        this.activity_location = activity_location;
    }

    public String getActivity_start_time() {
        return activity_start_time;
    }

    public void setActivity_start_time(String activity_start_time) {
        this.activity_start_time = activity_start_time;
    }

    public String getActivity_remark() {
        return activity_remark;
    }

    public void setActivity_remark(String activity_remark) {
        this.activity_remark = activity_remark;
    }

    public String getActivity_content() {
        return activity_content;
    }

    public void setActivity_content(String activity_content) {
        this.activity_content = activity_content;
    }

    public String getAssociation_name() {
        return association_name;
    }

    public void setAssociation_name(String association_name) {
        this.association_name = association_name;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_pic_url() {
        return activity_pic_url;
    }

    public void setActivity_pic_url(String activity_pic_url) {
        this.activity_pic_url = activity_pic_url;
    }

    public String getActivity_send_date() {
        return activity_send_date;
    }

    public void setActivity_send_date(String activity_send_date) {
        this.activity_send_date = activity_send_date;
    }

    public String getActivity_end_time() {
        return activity_end_time;
    }

    public void setActivity_end_time(String activity_end_time) {
        this.activity_end_time = activity_end_time;
    }

    public String getActivity_tailor_br() {
        return activity_tailor_br;
    }

    public void setActivity_tailor_br(String activity_tailor_br) {
        this.activity_tailor_br = activity_tailor_br;
    }

    public String getActivity_tailor_tl() {
        return activity_tailor_tl;
    }

    public void setActivity_tailor_tl(String activity_tailor_tl) {
        this.activity_tailor_tl = activity_tailor_tl;
    }

    public String getActivity_nav_color() {
        return activity_nav_color;
    }

    public void setActivity_nav_color(String activity_nav_color) {
        this.activity_nav_color = activity_nav_color;
    }

    public ActionItem toActionItem() {
        ActionItem item = new ActionItem();
        item.mAddr = this.activity_location;
        item.mActionName = this.activity_name;
        item.mSendTime = this.activity_send_date;
        item.mCollegaName = this.association_name;
        item.mHtmlContent = this.activity_content;
        item.mPicUrl = this.activity_pic_url;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(activity_start_time);
        } catch (ParseException e) {
            e.printStackTrace();
            return item;
        }
        String[] dateStr = Utility.formatDate(date);
        item.mDay = dateStr[2];
        item.mMouth = dateStr[1];
        item.mTime = dateStr[3];
        return item;
    }
}

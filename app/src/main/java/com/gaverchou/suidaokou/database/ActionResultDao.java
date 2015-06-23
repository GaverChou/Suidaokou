package com.gaverchou.suidaokou.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.gaverchou.suidaokou.model.ActionResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrgaver on 15-6-13.
 */
public class ActionResultDao extends DataDao {

    private static final String TAG = "ActionResultDao";
    protected static final String TAB_NAME = "ActionResult";

    public ActionResultDao(Context context) {
        super(context);
        createTable();
    }

    public void createTable() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TAB_NAME + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT,id varchar(16),activity_location varchar(16)," +
                    "activity_start_time varchar(16)," +
                    "activity_remark varchar(16)," +
                    "activity_content text," +
                    "association_name varchar(16)," +
                    "activity_name varchar(16)," +
                    "activity_pic_url text," +
                    "activity_send_date varchar(16)," +
                    "activity_end_time varchar(16)," +
                    "activity_tailor_br varchar(16)," +
                    "activity_tailor_tl varchar(16)," +
                    "activity_nav_color varchar(16));");//INTEGER为整型
            Log.v(TAG, "Create Table " + TAB_NAME + " ok");
        } catch (Exception e) {
            Log.v(TAG, "Create Table " + TAB_NAME + " err,table exists.");
        }
    }

    public void addActionResult(List<ActionResult> actionResults) {
        db.beginTransaction();  //开始事务
        try {
            for (ActionResult result : actionResults) {
                Log.d(TAG,"_ID:"+result.getId());
                db.execSQL("INSERT INTO " + TAB_NAME + " VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{result.getId(), result.getActivity_location(), result.getActivity_start_time(), result.getActivity_remark(),
                                result.getActivity_content(), result.getAssociation_name(), result.getActivity_name(), result.getActivity_pic_url(),
                                result.getActivity_send_date(), result.getActivity_end_time(), result.getActivity_tailor_br(), result.getActivity_tailor_tl(),
                                result.getActivity_nav_color()});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    public List<ActionResult> query() {
        ArrayList<ActionResult> actionResults = new ArrayList<ActionResult>();
        Cursor c = null;
        try {
            c = queryTheCursor();
            while (c.moveToNext()) {
                ActionResult result = new ActionResult();
                result.setId(c.getString(c.getColumnIndex("id")));
                result.setActivity_content(c.getString(c.getColumnIndex("activity_content")));
                result.setActivity_end_time(c.getString(c.getColumnIndex("activity_end_time")));
                result.setActivity_location(c.getString(c.getColumnIndex("activity_location")));
                result.setActivity_name(c.getString(c.getColumnIndex("activity_name")));
                result.setActivity_nav_color(c.getString(c.getColumnIndex("activity_nav_color")));
                result.setActivity_pic_url(c.getString(c.getColumnIndex("activity_pic_url")));
                result.setActivity_remark(c.getString(c.getColumnIndex("activity_remark")));
                result.setActivity_send_date(c.getString(c.getColumnIndex("activity_send_date")));
                result.setActivity_start_time(c.getString(c.getColumnIndex("activity_start_time")));
                result.setActivity_tailor_br(c.getString(c.getColumnIndex("activity_tailor_br")));
                result.setActivity_tailor_tl(c.getString(c.getColumnIndex("activity_tailor_tl")));
                result.setAssociation_name(c.getString(c.getColumnIndex("association_name")));
                actionResults.add(result);
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return actionResults;
    }

    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM " + TAB_NAME, null);
        return c;
    }

    public void deleteAll() {
        db.execSQL("delete from " + TAB_NAME);
        Log.v(TAG, "Delete all ActionResult!");
    }
}

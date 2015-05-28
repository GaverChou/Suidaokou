package com.gaverchou.suidaokou;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

/**
 * Created by GaverChou on 2015-05-27.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    protected void initView(){};
    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }
}

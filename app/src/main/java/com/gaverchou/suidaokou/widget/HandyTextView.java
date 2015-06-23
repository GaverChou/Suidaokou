package com.gaverchou.suidaokou.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.util.TypefaceManager;

/**
 * Created by GaverChou on 2015-05-31.
 */
public class HandyTextView extends TextView {
    private String mTTFName;

    public HandyTextView(Context context) {
        this(context, null);
    }

    public HandyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HandyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initTextView(Context context, AttributeSet attrs) {
        final TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.HandyTextView);
        mTTFName = array.getString(R.styleable.HandyTextView_textTTF);
        this.setTypeface(new TypefaceManager(context.getAssets()).getTTFByName(mTTFName));
    }
}

package com.gaverchou.suidaokou.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.gaverchou.observablescrollview.ObservableListView;
import com.gaverchou.suidaokou.util.TouchTool;

/**
 * Created by GaverChou on 2015-06-05.
 */
public class ImageRefreshListView extends ObservableListView {
    public ImageRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ImageRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private Scroller mScroller;

    private View headView;
    protected View bgView;

    public void setBgView(View bgView) {
        this.bgView = bgView;
    }

    public View getHeadView() {
        return headView;
    }

    public void setHeadView(View headView) {
        this.headView = headView;
    }

    private void initView(Context mContext) {
        mScroller = new Scroller(mContext);
    }

    TouchTool tool;
    int left, top;
    float startX, startY;
    int bgViewH, iv1W;
    int rootW, rootH;
    boolean scrollerType;
    static final int len = 0xc8;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        final float x = ev.getX();
        final float y = ev.getY();
        final int action = ev.getAction();

        if (!mScroller.isFinished()) {
            return super.onTouchEvent(ev);
        }
        if (headView == null || bgView == null) {
            return super.onTouchEvent(ev);
        }

//        bgView = headView.findViewById(R.id.path_headimage);

        headView.getTop();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                left = bgView.getLeft();
                top = bgView.getBottom();
                rootW = getWidth();
                rootH = getHeight();
                bgViewH = bgView.getHeight();
                startX = x;
                startY = y;
                tool = new TouchTool(bgView.getLeft(), bgView.getBottom());
                break;
            case MotionEvent.ACTION_MOVE:
                if (headView.isShown() && headView.getTop() >= 0) {
                    if (tool != null) {
                        int t = tool.getScrollY(y - startY);
                        if (t >= top && t <= headView.getBottom() + len) {
                            bgView.setLayoutParams(new LinearLayout.LayoutParams(
                                    bgView.getWidth(), t));
                            // proView.getValue()
                        }
                    }
                    scrollerType = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                scrollerType = true;
                // if(bgView.getBottom()-bgViewH>100&&re!=null){
                // re.refresh();
                // }
                mScroller.startScroll(bgView.getLeft(), bgView.getBottom(),
                        0 - bgView.getLeft(), bgViewH - bgView.getBottom(), 200);
                invalidate();
                break;
        }

        // call super if this was not our pinned view
        return super.dispatchTouchEvent(ev);
    }
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            bgView.layout(0, 0, x + bgView.getWidth(), y);
            invalidate();
            if (!mScroller.isFinished() && scrollerType && y > 200) {
                bgView.setLayoutParams(new LinearLayout.LayoutParams(bgView
                        .getWidth(), y));
            }
        }
    }
}

package com.gaverchou.suidaokou.util;

/**
 * Created by GaverChou on 2015-06-05.
 */
public class TouchTool {
    private int startX, startY;

    public TouchTool(int startX, int startY) {
        super();
        this.startX = startX;
        this.startY = startY;
    }

    public int getScrollX(float dx) {
        int xx = (int) (startX + dx / 2.5F);
        return xx;
    }

    public int getScrollY(float dy) {
        int yy = (int) (startY + dy / 2.5F);
        return yy;
    }
}

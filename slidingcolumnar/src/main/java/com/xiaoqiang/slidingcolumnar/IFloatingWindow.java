package com.xiaoqiang.slidingcolumnar;

import android.graphics.Canvas;

public interface IFloatingWindow {
    /**
     * 设置点击的 数据
     *
     * @param data
     */
    void setStepsTableData(ISlidingColumnar view, StepsTableData data);

    /**
     * 绘制
     *
     * @param canvas
     */
    void drawWindow(Canvas canvas);
}

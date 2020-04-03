package com.xiaoqiang.slidingcolumnar.listener;

import android.graphics.Canvas;

import com.xiaoqiang.slidingcolumnar.entity.StepsTableData;

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

package com.xiaoqiang.slidingcolumnar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.List;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/4/3 16:55
 */
public interface ISlidingColumnar {
    /**
     * 初始化  stepCount
     *
     * @param context
     * @param attrs
     */
    void init(Context context, AttributeSet attrs);

    /**
     * 设置 浮窗 绘制对象
     * @param windowDraw
     */
    void setFloatingWindowDraw(IFloatingWindow windowDraw);

    /**
     * 生成  背景表格 对象
     *
     * @param stepNumber
     */
    void setBottomTableData(List<BottomTableData> stepNumber);

    /**
     * 获取
     * 步数
     *
     * @return
     */
    List<BottomTableData> getBottomTableData();

    /**
     * 画 背景 步数表格
     *
     * @param canvas
     * @param paint
     * @param bottomTableData
     */
    void drawBottomTable(Canvas canvas, Paint paint, List<BottomTableData> bottomTableData);


    /**
     * 设置步数 和日期
     *
     * @param steps
     */
    void setStepsTableData(List<StepsTableData> steps);

    /**
     * 设置 高度  和 底部位置
     *
     * @param step
     * @param rectF
     * @return 当前 高度
     */
    void setHighAndLow(int step, RectF rectF);

    /**
     * 获取 时间步数
     *
     * @return
     */
    List<StepsTableData> getStepsTableData();

    /**
     * 画 时间 步数表格
     *
     * @param canvas
     * @param paint
     * @param stepsTableData
     */
    void drawStepsTableData(Canvas canvas, Paint paint, List<StepsTableData> stepsTableData);


    /**
     * 检测事件
     *
     * @param event
     * @return 如果 处理此事件 返回true 不处理 返回 false
     */
    boolean detectionEvent(MotionEvent event);

    /**
     * 设置 当前选中 事件
     *
     * @param data
     */
    void generateFloatingWindowData(StepsTableData data);

    /**
     * 绘制 当前点击的window
     *
     * @param canvas
     */
    void drawCurrentWindow(Canvas canvas);

}

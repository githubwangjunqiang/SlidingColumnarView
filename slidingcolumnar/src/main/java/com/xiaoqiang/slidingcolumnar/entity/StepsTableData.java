package com.xiaoqiang.slidingcolumnar.entity;

import android.graphics.RectF;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/4/3 17:05
 */
public class StepsTableData {
    /**
     * 柱状图 范围矩形
     */
    private RectF mRectF;
    /**
     *柱状图 底部日期文案
     */
    private String text;
    /**
     * 点击事件出发后 浮窗显示的日期文案
     */
    private String textWindow;
    /**
     * 步数 数值
     */
    private int step;
    /**
     *柱状图 渐变颜色 开始颜色
     */
    private int startLineColor;
    /**
     *柱状图 渐变颜色 结束颜色
     */
    private int endLineColor;
    /**
     *日期字体颜色 柱状图底部
     */
    private int textColor;
    /**
     *日期字体大小 柱状图底部
     */
    private float textSize;

    public String getTextWindow() {
        return textWindow == null ? "" : textWindow;
    }

    public void setTextWindow(String textWindow) {
        this.textWindow = textWindow;
    }

    /**
     * 是否 是此区域
     *
     * @param downx
     * @param downy
     * @return
     */
    public boolean onClick(float downx, float downy, float range) {

        RectF rectF = new RectF(mRectF.left - range, 0, mRectF.right + range, mRectF.bottom);
        return rectF.contains(downx, downy);
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public RectF getRectF() {
        return mRectF;
    }

    public void setRectF(RectF rectF) {
        mRectF = rectF;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStartLineColor() {
        return startLineColor;
    }

    public void setStartLineColor(int startLineColor) {
        this.startLineColor = startLineColor;
    }

    public int getEndLineColor() {
        return endLineColor;
    }

    public void setEndLineColor(int endLineColor) {
        this.endLineColor = endLineColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }
}

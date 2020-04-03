package com.xiaoqiang.slidingcolumnar;

import android.graphics.RectF;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/4/3 17:05
 */
public class StepsTableData {

    private RectF mRectF;
    private String text;
    private String textWindow;
    private int step;
    private int startLineColor;
    private int endLineColor;
    private int textColor;
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

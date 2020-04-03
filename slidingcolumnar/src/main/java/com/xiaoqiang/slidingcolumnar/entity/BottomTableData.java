package com.xiaoqiang.slidingcolumnar.entity;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/4/3 17:00
 */
public class BottomTableData {
    /**
     * 步数值 此行显示多少值
     */
    private int number;
    /**
     * 横线 开始的x
     */
    private float lineStartx;
    /**
     * 横线 开始的y
     */
    private float lineStarty;
    /**
     * 数值字体颜色
     */
    private int colorText;
    /**
     * 横线颜色
     */
    private int colorLine;
    /**
     * 数值字体大小
     */
    private float textSize;
    /**
     * 横线本身高度
     */
    private float lineWidth;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getLineStartx() {
        return lineStartx;
    }

    public void setLineStartx(float lineStartx) {
        this.lineStartx = lineStartx;
    }

    public float getLineStarty() {
        return lineStarty;
    }

    public void setLineStarty(float lineStarty) {
        this.lineStarty = lineStarty;
    }

    public int getColorText() {
        return colorText;
    }

    public void setColorText(int colorText) {
        this.colorText = colorText;
    }

    public int getColorLine() {
        return colorLine;
    }

    public void setColorLine(int colorLine) {
        this.colorLine = colorLine;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
}

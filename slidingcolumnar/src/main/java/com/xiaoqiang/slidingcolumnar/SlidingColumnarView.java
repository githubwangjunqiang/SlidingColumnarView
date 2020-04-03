package com.xiaoqiang.slidingcolumnar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoqiang.slidingcolumnar.entity.BottomTableData;
import com.xiaoqiang.slidingcolumnar.entity.StepsTableData;
import com.xiaoqiang.slidingcolumnar.listener.IFloatingWindow;
import com.xiaoqiang.slidingcolumnar.listener.ISlidingColumnar;

import java.util.List;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/4/3 16:54
 */
public class SlidingColumnarView extends View implements ISlidingColumnar {
    /**
     * 表格 底部边距
     */
    private float bottomTablePadding;
    /**
     * 表格 左边距
     */
    private float leftTablePadding;
    /**
     * 表格 右边距  控件右边距
     */
    private float riteTablePadding;
    /**
     * 表格 间距
     */
    private float tableBottomInterval;
    /**
     * 表格对象
     */
    private List<BottomTableData> mBottomTableData;
    /**
     * 步数 日期对象
     */
    private List<StepsTableData> mStepsTableData;
    /**
     * 底部 表格 画笔
     */
    private Paint mPaintBack;

    /**
     * 步数 柱状图 画笔
     */
    private Paint mPaintStep;
    /**
     * 日期 步数 左边距
     */
    private float leftStepPadding;
    /**
     * 日期 间距
     */
    private float stepSpacing;
    /**
     * 时间 柱状 宽度
     */
    private float mStepWidth;

    /**
     * 日期文字 与 柱状图 间距
     */
    private float mStepTextTopPadding;

    /**
     * 用户点击的 xy
     */
    private float downX, downY;

    private IFloatingWindow mFloatingWindow;

    public IFloatingWindow getFloatingWindow() {
        return mFloatingWindow;
    }

    public SlidingColumnarView(Context context) {
        this(context, null);
    }

    public SlidingColumnarView(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingColumnarView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        setClickable(true);
        bottomTablePadding = dpTopx(37);
        leftTablePadding = dpTopx(51);
        riteTablePadding = dpTopx(71);
        tableBottomInterval = dpTopx(30);
        leftStepPadding = dpTopx(67);
        stepSpacing = dpTopx(30);
        mStepWidth = dpTopx(8);
        mStepTextTopPadding = dpTopx(9);

        mPaintBack = new Paint();
        mPaintBack.setAntiAlias(true);
        mPaintBack.setStyle(Paint.Style.STROKE);
        mPaintBack.setTextAlign(Paint.Align.RIGHT);

        mPaintStep = new Paint();
        mPaintStep.setAntiAlias(true);
        mPaintStep.setStyle(Paint.Style.FILL);
        mPaintStep.setTextAlign(Paint.Align.CENTER);

        setFloatingWindowDraw(new FloatingWindow(context));

    }

    @Override
    public void setFloatingWindowDraw(IFloatingWindow windowDraw) {
        mFloatingWindow = windowDraw;
        postInvalidate();
    }

    private float dpTopx(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getContext().getResources().getDisplayMetrics());
    }


    @Override
    public boolean performClick() {
//        detectionEvent()
        Log.d("12345", "performClick: ");
        List<StepsTableData> stepTimeData = getStepsTableData();
        if (stepTimeData != null) {
            for (int i = 0; i < stepTimeData.size(); i++) {
                StepsTableData data = stepTimeData.get(i);
                if (data.onClick(downX, downY, tableBottomInterval / 2)) {
                    generateFloatingWindowData(data);
                    break;
                }
            }
        }
        return super.performClick();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            downY = event.getY();
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<BottomTableData> stepNumberDatas1 = getBottomTableData();
        drawBottomTable(canvas, mPaintBack, stepNumberDatas1);

        List<StepsTableData> stepTimeData = getStepsTableData();

        drawStepsTableData(canvas, mPaintStep, stepTimeData);

        drawCurrentWindow(canvas);

    }


    public float getBottomTablePadding() {
        return bottomTablePadding;
    }

    public void setBottomTablePadding(float bottomTablePadding) {
        this.bottomTablePadding = bottomTablePadding;
    }

    public float getLeftTablePadding() {
        return leftTablePadding;
    }

    public void setLeftTablePadding(float leftTablePadding) {
        this.leftTablePadding = leftTablePadding;
    }

    public float getRiteTablePadding() {
        return riteTablePadding;
    }

    public void setRiteTablePadding(float riteTablePadding) {
        this.riteTablePadding = riteTablePadding;
    }

    public float getTableBottomInterval() {
        return tableBottomInterval;
    }

    public void setTableBottomInterval(float tableBottomInterval) {
        this.tableBottomInterval = tableBottomInterval;
    }

    public float getLeftStepPadding() {
        return leftStepPadding;
    }

    public void setLeftStepPadding(float leftStepPadding) {
        this.leftStepPadding = leftStepPadding;
    }

    public float getStepSpacing() {
        return stepSpacing;
    }

    public void setStepSpacing(float stepSpacing) {
        this.stepSpacing = stepSpacing;
    }

    public float getStepWidth() {
        return mStepWidth;
    }

    public void setStepWidth(float stepWidth) {
        mStepWidth = stepWidth;
    }

    public float getStepTextTopPadding() {
        return mStepTextTopPadding;
    }

    public void setStepTextTopPadding(float stepTextTopPadding) {
        mStepTextTopPadding = stepTextTopPadding;
    }

    @Override
    public void setBottomTableData(List<BottomTableData> stepNumber) {
        if (stepNumber == null || stepNumber.isEmpty()) {
            return;
        }
        if (mBottomTableData == null) {
            mBottomTableData = stepNumber;
        } else {
            mBottomTableData.clear();
            mBottomTableData.addAll(stepNumber);
        }


        int size = mBottomTableData.size();
        for (int i = size - 1; i >= 0; i--) {
            BottomTableData data = mBottomTableData.get(i);
            data.setLineStartx(leftTablePadding);
            data.setLineStarty((size - i) * tableBottomInterval + dpTopx(5));
        }
        BottomTableData data = mBottomTableData.get(0);
        float viewHeight = data.getLineStarty() + bottomTablePadding;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = (int) (viewHeight + riteTablePadding);
        setLayoutParams(layoutParams);
        postInvalidate();
    }


    @Override
    public List<BottomTableData> getBottomTableData() {
        return mBottomTableData;
    }


    @Override
    public void drawBottomTable(Canvas canvas, Paint paint, List<BottomTableData> bottomTableData) {

        if (bottomTableData == null) {
            return;
        }
        for (int i = 0; i < bottomTableData.size(); i++) {
            BottomTableData data = bottomTableData.get(i);
            paint.setColor(data.getColorLine());
            paint.setStrokeWidth(data.getLineWidth());
            canvas.drawLine(data.getLineStartx(), data.getLineStarty(), getWidth() - riteTablePadding, data.getLineStarty(), paint);
            paint.setColor(data.getColorText());
            paint.setTextSize(data.getTextSize());
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float v = (Math.abs(fontMetrics.ascent) + Math.abs(fontMetrics.descent)) / 2 - Math.abs(fontMetrics.descent);
            canvas.drawText(data.getNumber() + "", data.getLineStartx(), data.getLineStarty() + v, paint);
        }

    }


    @Override
    public void setStepsTableData(List<StepsTableData> steps) {
        if (steps == null || steps.isEmpty()) {
            return;
        }
        if (mStepsTableData == null) {
            mStepsTableData = steps;
        } else {
            mStepsTableData.clear();
            mStepsTableData.addAll(steps);
        }


        float viewWidth = 0;
        for (int i = 0; i < mStepsTableData.size(); i++) {
            StepsTableData data = mStepsTableData.get(i);
            RectF rectF = new RectF();
            rectF.left = leftStepPadding + i * stepSpacing + i * mStepWidth;
            setHighAndLow(data.getStep(), rectF);
            rectF.right = rectF.left + mStepWidth;
            data.setRectF(rectF);
            viewWidth = rectF.right;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = (int) (viewWidth + riteTablePadding);
        setLayoutParams(layoutParams);
        postInvalidate();

    }


    @Override
    public void setHighAndLow(int step, RectF rectF) {

        List<BottomTableData> stepNumberDatas = getBottomTableData();
        if (stepNumberDatas == null || stepNumberDatas.isEmpty()) {
            return;
        }
        BottomTableData stepNumberData = stepNumberDatas.get(stepNumberDatas.size() - 1);
        float stopy = stepNumberData.getLineStarty();
        float starty = stepNumberDatas.get(0).getLineStarty();
        float v = starty - stopy;
        float px = v * step / stepNumberData.getNumber();

        float height = starty - px;

        rectF.top = height;
        rectF.bottom = starty;
    }


    @Override
    public List<StepsTableData> getStepsTableData() {
        return mStepsTableData;
    }


    @Override
    public void drawStepsTableData(Canvas canvas, Paint paint, List<StepsTableData> stepsTableData) {
        if (paint == null || stepsTableData == null || stepsTableData.isEmpty()) {
            return;
        }

        for (int i = 0; i < stepsTableData.size(); i++) {
            StepsTableData data = stepsTableData.get(i);
            paint.setShader(null);
            paint.setColor(data.getTextColor());
            paint.setTextSize(data.getTextSize());
            RectF rectFLine = data.getRectF();
            float textStartx = rectFLine.left + rectFLine.width() / 2;

            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float bottom = rectFLine.bottom + mStepTextTopPadding + Math.abs(fontMetrics.ascent);
            canvas.drawText(data.getText(), textStartx, bottom, paint);

            LinearGradient linearGradient = new LinearGradient(
                    rectFLine.left,
                    rectFLine.top,
                    rectFLine.right,
                    rectFLine.bottom,
                    new int[]{data.getStartLineColor(), data.getEndLineColor()}, null, Shader.TileMode.CLAMP);
            paint.setShader(linearGradient);
            canvas.drawRoundRect(rectFLine, rectFLine.width() / 2, rectFLine.width() / 2, paint);
        }
    }


    @Override
    public boolean detectionEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            downY = event.getY();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (Math.abs(Math.abs(event.getX()) - Math.abs(downX)) > 20) {
                return false;
            }
            if (Math.abs(Math.abs(event.getY()) - Math.abs(downY)) > 20) {
                return false;
            }
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {

            List<StepsTableData> stepTimeData = getStepsTableData();
            if (stepTimeData != null) {

                for (int i = 0; i < stepTimeData.size(); i++) {
                    StepsTableData data = stepTimeData.get(i);
                    if (data.onClick(downX, downY, tableBottomInterval / 2)) {
                        generateFloatingWindowData(data);
                        break;
                    }
                }
            }
            return true;
        }

        return false;
    }


    @Override
    public void generateFloatingWindowData(StepsTableData data) {
        if (mFloatingWindow != null) {
            try {
                mFloatingWindow.setStepsTableData(this, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        postInvalidate();
    }


    @Override
    public void drawCurrentWindow(Canvas canvas) {
        if (mFloatingWindow != null) {
            try {
                mFloatingWindow.drawWindow(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

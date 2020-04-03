package com.xiaoqiang.slidingcolumnar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.TypedValue;

import com.xiaoqiang.slidingcolumnar.entity.BottomTableData;
import com.xiaoqiang.slidingcolumnar.entity.StepsTableData;
import com.xiaoqiang.slidingcolumnar.listener.IFloatingWindow;
import com.xiaoqiang.slidingcolumnar.listener.ISlidingColumnar;

import java.util.List;

public class FloatingWindow implements IFloatingWindow {
    /**
     * 底部 表格 画笔
     */
    private Paint mPaintWinDow;
    private String date;
    private int step;
    private float centerX;
    private float centerY;
    private float bottomY;
    private float topY;
    private int width;
    private int lineColor;
    private int circleColor;
    private int textColor;
    private int backColor;
    private float textSize;
    private float radius;
    private Context mContext;
    private Path mPath;

    public FloatingWindow(Context centerx) {
        this.mContext = centerx;
        if (mPaintWinDow == null) {
            mPaintWinDow = new Paint();
            mPaintWinDow.setAntiAlias(true);
            mPaintWinDow.setStyle(Paint.Style.FILL);

            lineColor = Color.parseColor("#8c607EFF");
            textColor = Color.parseColor("#ffffff");
            circleColor = Color.parseColor("#FFAA60");
            backColor = Color.parseColor("#FEAB63");

            textSize = dpTopx(12);
            width = (int) dpTopx(1);
            radius = dpTopx(4.5f);
            mPaintWinDow.setTextSize(textSize);
            mPaintWinDow.setColor(lineColor);

            mPaintWinDow.setStrokeWidth(width);
        }
        mPath = new Path();
    }

    private float dpTopx(float size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                size, mContext.getResources().getDisplayMetrics());
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    @Override
    public void setStepsTableData(ISlidingColumnar view, StepsTableData data) {

        date = data.getTextWindow();
        step = data.getStep();
        centerX = data.getRectF().left + data.getRectF().width() * 0.5f;
        List<BottomTableData> bottomTableData = view.getBottomTableData();
        centerY = bottomTableData.get(bottomTableData.size() - 1).getLineStarty();
        bottomY = data.getRectF().bottom;
        topY = 0;

    }

    @Override
    public void drawWindow(Canvas canvas) {

        if (TextUtils.isEmpty(date) && step == 0) {
            return;
        }
        mPaintWinDow.setColor(lineColor);
        canvas.drawLine(centerX, topY, centerX, bottomY, mPaintWinDow);

        mPaintWinDow.setColor(circleColor);
        canvas.drawCircle(centerX, centerY, radius, mPaintWinDow);

        mPaintWinDow.setColor(backColor);
        Path path = mPath;
        path.reset();
        RectF rect = new RectF(
                (int) (centerX + radius + dpTopx(10)),
                (int) (centerY - dpTopx(35)),
                (int) (centerX + radius + dpTopx(10 + 60)),
                (int) (centerY)
        );
        float v = dpTopx(6);
        path.addRoundRect(rect, v, v, Path.Direction.CCW);
        canvas.drawPath(path, mPaintWinDow);
        path.reset();
        path.moveTo(centerX + radius + dpTopx(3), centerY);
        path.lineTo(rect.left + v, centerY - dpTopx(15));
        path.lineTo(rect.left + v, centerY);
        path.lineTo(centerX + radius + dpTopx(3), centerY);
        canvas.drawPath(path, mPaintWinDow);

        mPaintWinDow.setColor(textColor);
        Paint.FontMetrics fontMetrics = mPaintWinDow.getFontMetrics();
        float d = Math.abs(fontMetrics.descent);
        float ascent = Math.abs(fontMetrics.ascent);
        canvas.drawText(step + "步",
                rect.left + dpTopx(11),
                rect.top + rect.height() / 2 + ascent,
                mPaintWinDow);
        mPaintWinDow.setColor(Color.parseColor("#E3ffffff"));
        canvas.drawText(date,
                rect.left + dpTopx(6),
                rect.top + rect.height() / 2 - d,
                mPaintWinDow);
    }
}

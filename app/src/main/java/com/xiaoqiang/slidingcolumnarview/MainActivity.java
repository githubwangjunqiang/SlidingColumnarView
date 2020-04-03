package com.xiaoqiang.slidingcolumnarview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import com.xiaoqiang.slidingcolumnar.BottomTableData;
import com.xiaoqiang.slidingcolumnar.SlidingColumnarView;
import com.xiaoqiang.slidingcolumnar.StepsTableData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SlidingColumnarView mFormLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFormLineView = findViewById(R.id.fview);


        List<BottomTableData> mStepNumberData = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            BottomTableData data = new BottomTableData();
            data.setColorLine(Color.parseColor("#F5F5F5"));
            data.setColorText(Color.parseColor("#999999"));
            data.setTextSize(dpToPx(this, 12));
            data.setNumber(i * 2000);
            data.setLineWidth(dpToPx(this, 1));
            mStepNumberData.add(data);
        }

        mFormLineView.setBottomTableData(mStepNumberData);


        List<StepsTableData> list = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            StepsTableData stepBean = new StepsTableData();
            stepBean.setStep(new Random().nextInt(10000) + 500);
            stepBean.setText((i + 1) + "日");
            stepBean.setTextWindow("12/" + (i + 1));
            stepBean.setTextSize(dpToPx(this, 12));
            stepBean.setTextColor(Color.parseColor("#999999"));
            stepBean.setStartLineColor(Color.parseColor("#7DC3FF"));
            stepBean.setEndLineColor(Color.parseColor("#6D84FF"));
            list.add(stepBean);
        }
        mFormLineView.setStepsTableData(list);
    }

    private float dpToPx(MainActivity mainActivity, int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics());
    }
}

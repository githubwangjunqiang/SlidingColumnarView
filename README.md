# SlidingColumnarView

Android 小强 编写的简单柱状图 有兴趣的同学 可以参考学习  目前项目中步数月历试图 在使用

1、Add it in your root build.gradle at the end of repositories:

  	allprojects {
  		repositories {
  			...
  			maven { url 'https://jitpack.io' }
  		}
  	}
  Step 2. Add the dependency

  	dependencies {
  	        implementation 'com.github.githubwangjunqiang:SlidingColumnarView:v1.0'
  	}

2、xml 中引用

    <HorizontalScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:scrollbars="none">

        <FrameLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content">

            <com.xiaoqiang.slidingcolumnar.SlidingColumnarView
                android:id="@+id/fview"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />
        </FrameLayout>
    </HorizontalScrollView>


3、代码中设置数据 底部表格 和 步数表格
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


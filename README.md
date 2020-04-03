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

4、 默认点击柱状图后会显示 浮窗  ，如果对浮窗有要求 可以自己实现IFloatingWindow 接口
          // 可以参考 代码中 已经实现的 FloatingWindow
          // 最后 设置给 mFormLineView 即可

            //此接口 的抽象方法 有两个
              /**
               * 设置点击的 数据
               *
               * @param view  控件本身 可以用来获取一些数据
               * @param data  用户点击的 柱状图数据 里面含有柱状图相关的坐标
               */
              void setStepsTableData(ISlidingColumnar view, StepsTableData data);

              /**
               * 绘制
               *
               * @param canvas
               */
              void drawWindow(Canvas canvas);
          //--------------------------------


          //例如 可以使用内部类  建议不要使用内部类
        mFormLineView.setFloatingWindowDraw(new IFloatingWindow() {
            @Override
            public void setStepsTableData(ISlidingColumnar view, StepsTableData data) {
                //当确认用户点击行为时调用
            }

            @Override
            public void drawWindow(Canvas canvas) {

            }
        });


5、实体类 说明：

        BottomTableData  底部步数 行数据

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




         StepsTableData  步数数据 实体类 柱状图数据

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
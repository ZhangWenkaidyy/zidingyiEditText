package com.kevin.zwk.kt_zidingyi_0618_1;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/6/18.
 */
public class MyEdit extends EditText {
    private int Width;
    private Paint paint;
    private int Height;
    int max = 20;
    private int textColor;
    private  int textSize;
    private String text = "开始写了";

    //生成代码时 Button button=new Button（this）;
    public MyEdit(Context context) {
        super(context);
        init(null, context);
    }

    //xml文件中，AttributeSet存长宽
    public MyEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    //新的材料設計裏面的皮膚style
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyEdit(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, context);
    }

    //存style
    public MyEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    private void init(AttributeSet attrs, Context context) {

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyEdit);
            textColor=typedArray.getColor(R.styleable.MyEdit_myTextColor, Color.BLUE);
            textSize= (int) typedArray.getDimension(R.styleable.MyEdit_myTextSize,DpUtils.dip2px(context,15));
            Log.i("tag","---textSize"+textSize);

        }
        paint = new Paint();
        paint.setColor(textColor);
        paint.setStrokeWidth(10);
        paint.setTextSize(textSize);
//输入过滤器
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//维持原有的测量方法
        Width = getWidth();
        Height = getHeight();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int left = max - s.length();
                if (left >= 0) {
                    text = "还剩" + left + "字";
                } else {
                    text = "不能写了";
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);//维持edittext原有的绘制
        //在右下角绘制文本
        canvas.drawText(text, Width-paint.measureText(text), getScrollY()+Height-textSize, paint);
        // canvas.drawLine(0, Height / 2, Width, Height / 2, paint);
    }
}

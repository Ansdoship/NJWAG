package com.ansdoship.paronomasia.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.ansdoship.paronomasia.utils.ScreenUtils;

public class LocalProgressBar extends View {

    private String name;
    private int height;
    private String color;
    private String cornerColor;
    private double dataVAlue;
    private double MaxValue;

    public LocalProgressBar(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int screenWidth = ScreenUtils.getWidth(getContext());
        int screenHeight = ScreenUtils.getHeight(getContext());

    }
}

package com.ansdoship.paronomasia.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.ansdoship.paronomasia.model.DataType;

public class Container implements DataListener{

    private Context context;
    private FrameLayout containerLayout;

    public Container(Context context,int id){
        containerLayout = ((Activity)context).findViewById(id);
    }

    public void addView(View view,int x,int y){
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = view.getLeft()+x;
        params.topMargin = view.getTop()+y;
        containerLayout.addView(view,params);
    }

    @Override
    public void changed(String name, DataType dataType) {
        int size = containerLayout.getChildCount();
        for(int i = 0;i<size;i++){
            View item = containerLayout.getChildAt(i);
            if(item instanceof LocalTextView){
                ((LocalTextView)item).changed(name,dataType);
            }
        }
    }
}

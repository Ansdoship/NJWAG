package com.ansdoship.paronomasia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ansdoship.paronomasia.model.DataType;

public class LocalView extends View implements DataListener{

    public LocalView(Context context){
        super(context);
    }

    public LocalView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public void changed(String name, DataType dataType) {

    }
}

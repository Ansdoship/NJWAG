package com.ansdoship.paronomasia.view;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.ansdoship.paronomasia.model.DataType;

public class LocalTextView extends AppCompatTextView implements DataListener{

    private String dataBindName;

    public LocalTextView(Context context){
        super(context);
    }

    public void setDataBindName(String dataBindName) {
        this.dataBindName = dataBindName;
    }

    public String getDataBindName() {
        return dataBindName;
    }

    @Override
    public void changed(String name, DataType dataType) {
        if(name.equals(dataBindName)){
            setText(dataType.toString());
        }
    }
}

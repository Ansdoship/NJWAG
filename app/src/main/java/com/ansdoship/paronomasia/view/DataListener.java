package com.ansdoship.paronomasia.view;

import com.ansdoship.paronomasia.model.DataType;

public interface DataListener {
    void changed(String name,DataType dataType);
}

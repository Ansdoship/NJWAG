package com.ansdoship.paronomasia.model;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class DataType {

    public static final int NULL = 0;
    public static final int NUMBER = 1;
    public static final int STRING = 2;
    public static final int BOOLEAN = 3;

    private String strValue;
    private int type;

    public DataType(String str){
        this.strValue = str;
        this.type = STRING;
    }

    public DataType(Double value){
        this.strValue = String.valueOf(value);
        this.type = NUMBER;
    }

    public DataType(Boolean bool){
        this.strValue = String.valueOf(bool);
        this.type = BOOLEAN;
    }

    public DataType(){
        this.type = NULL;
    }

    public Double getNumber() {
        if(type==NUMBER){
            return Double.parseDouble(strValue);
        }else{
            setNumber(0d);
            return getNumber();
        }
    }

    public String getStr(){
        if(type==STRING){
            return strValue;
        }else{
            setStr("");
            return getStr();
        }
    }

    public Boolean getBool(){
        if(type==BOOLEAN){
            return Boolean.parseBoolean(strValue);
        }else{
            setBool(false);
            return getBool();
        }
    }

    public int getType(){
        return type;
    }

    public void setNumber(Double value) {
        this.type = NUMBER;
        this.strValue = String.valueOf(value);
    }

    public void setStr(String str){
        this.type = STRING;
        this.strValue = str;
    }

    public void setBool(Boolean value){
        this.type = BOOLEAN;
        this.strValue = String.valueOf(value);
    }

    public void setNull(){
        this.type = NULL;
    }

    @NonNull
    @Override
    public String toString() {
        if(strValue==null){
            return "null";
        }else {
            return strValue;
        }
    }
}

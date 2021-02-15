package com.ansdoship.paronomasia.model;

import android.util.Log;

import com.ansdoship.paronomasia.view.DataListener;
import com.udojava.evalex.Expression;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class VariablesPool extends HashMap<String,DataType> {

    public final String TAG = "VariablesPool";

    private DataListener dataListener;

    public VariablesPool(){
        super();
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    public void putVar(String expression){
        if(expression.contains(":")) {
            String[] temp = expression.split(":");
            String name = temp[0],value = temp[1];
            DataType dataType;
            if (isNumber(value)) {
                dataType = new DataType(Double.parseDouble(value));
                put(name, dataType);
            } else if (isBoolean(value)) {
                dataType = new DataType(Boolean.parseBoolean(value));
                put(name, dataType);
            } else {
                dataType = new DataType(value);
                put(name, dataType);
            }
            dataListener.changed(name,dataType);
        }else{
            throw new IllegalArgumentException("错误表达式");
        }
    }

    public void increase(String var){
        if(containsKey(var)){
            DataType item = get(var);
            double value = item.getNumber();
            value++;
            item.setNumber(value);
            put(var,new DataType(value));
            dataListener.changed(var,item);
        }
    }

    public void decrease(String var){
        if(containsKey(var)){
            DataType item = get(var);
            double value = item.getNumber();
            value--;
            item.setNumber(value);
            put(var,new DataType(value));
            dataListener.changed(var,item);
        }
    }

    public void eval(String value){
        String key = "",text = "";
        if(value.contains(":")){
            String[] temp = value.split(":");
            key = temp[0];
            text = temp[1];
        }else{
            throw new IllegalArgumentException("错误表达式");
        }
        Expression expression = new Expression(text);
        Iterator<Entry<String, DataType>> it = entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, DataType> entry = it.next();
            DataType item = entry.getValue();
            if(item.getType()==DataType.NUMBER) {
                expression.with(entry.getKey(), entry.getValue().toString());
            }
        }
        double result = expression.eval().doubleValue();
        DataType item = new DataType(result);
        put(key,item);
        dataListener.changed(key,item);
    }

    public void testPrint(){
        Iterator<Entry<String, DataType>> it = entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, DataType> entry = it.next();
            Log.i(TAG,entry.getKey()+":"+entry.getValue().toString());
        }
    }

    private boolean isNumber(String str) {
        boolean is = Pattern.compile("-?[0-9]+.*[0-9]*").matcher(str).find();

        return is;
    }

    private boolean isBoolean(String str){
        return "true".equalsIgnoreCase(str)||"false".equalsIgnoreCase(str);
    }
}

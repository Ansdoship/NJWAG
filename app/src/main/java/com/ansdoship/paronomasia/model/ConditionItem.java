package com.ansdoship.paronomasia.model;

import com.udojava.evalex.Expression;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class ConditionItem {

    private String chapterCondition;
    private String expressionCondition;
    private FunctionPool functionPool;

    public String getChapterCondition() {
        return chapterCondition;
    }

    public String getExpressionCondition() {
        return expressionCondition;
    }

    public FunctionPool getFunctionPool() {
        return functionPool;
    }

    public void setChapterCondition(String chapterCondition) {
        this.chapterCondition = chapterCondition;
    }

    public void setExpressionCondition(String expressionCondition) {
        this.expressionCondition = expressionCondition;
    }

    public void setFunctionPool(FunctionPool functionPool) {
        this.functionPool = functionPool;
    }

    public boolean judge(Game game){
        String chapterName = game.getStage().getCurrentChapter().getName();

        Expression expression = new Expression(expressionCondition);
        Iterator<Map.Entry<String, DataType>> it = game.getVariables().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, DataType> entry = it.next();
            DataType value = entry.getValue();
            if(value.getType()==DataType.NUMBER) {
                expression.with(entry.getKey(), entry.getValue().toString());
            }
        }
        int value = expression.eval().intValue();

        if(Pattern.matches("^"+chapterCondition+"$",chapterName)&&value==1){
            return true;
        }else{
            return false;
        }
    }
}

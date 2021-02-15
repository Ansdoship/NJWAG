package com.ansdoship.paronomasia.model;

import android.util.Log;

import java.util.Iterator;
import java.util.Map;

public class Game implements FunctionExecutor{

    private final String TAG = "Game";

    private String title;
    private VariablesPool variables;
    private Initializer initializer;
    private Stage stage;
    private Dynamic dynamicConditions;
    private FunctionExecutor functionExecutor;

    public Game(){
        functionExecutor = this;
    }

    public String getTitle() {
        return title;
    }

    public VariablesPool getVariables() {
        return variables;
    }

    public Stage getStage() {
        return stage;
    }

    public Dynamic getDynamicConditions() {
        return dynamicConditions;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVariables(VariablesPool variables) {
        this.variables = variables;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setDynamicConditions(Dynamic dynamicConditions) {
        this.dynamicConditions = dynamicConditions;
    }

    public void setInitializer(Initializer initializer) {
        this.initializer = initializer;
    }

    public void choiceTrigger(int index){
        ChoiceItem choiceItem = stage.getCurrentChapter().getChoices().get(index);
        FunctionPool functionPool = choiceItem.getFunctionPool();
        executeFunctionPool(functionPool);
        Log.i(TAG,"选择了"+choiceItem.getKeyword());

        judgeDynamicConditions();
    }

    public void build(){
        FunctionPool functionPool = initializer.getFunctionPool();
        executeFunctionPool(functionPool);
    }

    private void judgeDynamicConditions(){
        Conditions conditionItems = dynamicConditions.getConditionItems();
        for (int i = 0;i<conditionItems.size();i++){
            ConditionItem item = conditionItems.get(i);
            if(item.judge(this)){
                executeFunctionPool(item.getFunctionPool());
                break;
            }
        }
    }

    private void executeFunctionPool(FunctionPool pool){
        for(int i = 0;i<pool.size();i++){
            Function item = pool.get(i);
            functionExecutor.execute(item.getName(),item.getExpression());
        }
    }

    @Override
    public void execute(String key, String value) {
        switch (key){
            case "none":
                break;
            case "goto":
                stage.skipChapter(value);
                break;
            case "incr":
                variables.increase(value);
                break;
            case "decr":
                variables.decrease(value);
                break;
            case "calc":
                variables.eval(value);
                break;
            case "reset":
                stage.reset();
                break;
            case "var":
                variables.putVar(value);
                break;
            default:
                throw new IllegalArgumentException("错误方法名");
        }
    }
}

package com.ansdoship.paronomasia.model;

public class Function {

    private String name;
    private String expression;

    public Function(String name,String expression){
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public String getExpression() {
        return expression;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}

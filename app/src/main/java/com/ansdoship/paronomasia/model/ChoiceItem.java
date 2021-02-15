package com.ansdoship.paronomasia.model;

public class ChoiceItem {

    private String keyword;
    private String description;
    private FunctionPool functionPool;

    public String getKeyword() {
        return keyword;
    }

    public String getDescription() {
        return description;
    }

    public FunctionPool getFunctionPool() {
        return functionPool;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFunctionPool(FunctionPool functionPool) {
        this.functionPool = functionPool;
    }
}

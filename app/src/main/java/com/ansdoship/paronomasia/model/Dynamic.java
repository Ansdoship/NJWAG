package com.ansdoship.paronomasia.model;

public class Dynamic {

    private Conditions conditionItems = new Conditions();

    public Conditions getConditionItems() {
        return conditionItems;
    }

    public void addCondition(ConditionItem item){
        conditionItems.add(item);
    }

    public void removeCondition(ConditionItem item){
        conditionItems.remove(item);
    }

    public void clearCondition(){
        conditionItems.clear();
    }
}

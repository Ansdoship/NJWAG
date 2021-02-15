package com.ansdoship.paronomasia.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Stage{

    private List<Chapter> chapterList = new ArrayList<>();
    private int index = 0;

    public Stage(){

    }

    public void nextChapter(){
        if(index==chapterList.size()-1) index++;
    }

    public Chapter getChapter(int index){
        return chapterList.get(index);
    }

    public Chapter getCurrentChapter(){
        return chapterList.get(index);
    }

    public void skipChapter(String name){
        for (int i = 0;i<chapterList.size();i++){
            if (name.equals(chapterList.get(i).getName().trim())){
                index = i;
                return;
            }
        }
        throw new IllegalArgumentException("跳转到未定义章节");
    }

    public void reset(){
        index = 0;
    }

    public void addChapter(Chapter chapter){
        chapterList.add(chapter);
    }

    public void removeChapter(Chapter chapter){
        chapterList.remove(chapter);
    }

    public void clearAllChapter(){
        chapterList.clear();
    }

}

package com.ansdoship.paronomasia.manager;

import java.util.HashMap;
import java.util.Map;

public class PathManager {
    private static Map<String,String> map = new HashMap<>();

    public static void add(String key,String value){
        map.put(key,value);
    }

    public static void remove(String key){
        if(map.containsKey(key)) map.remove(key);
    }

    public static String get(String key) {
        if (map.containsKey(key)) return map.get(key);
        else return "";
    }
}

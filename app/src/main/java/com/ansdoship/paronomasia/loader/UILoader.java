package com.ansdoship.paronomasia.loader;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.ansdoship.paronomasia.R;
import com.ansdoship.paronomasia.manager.PathManager;
import com.ansdoship.paronomasia.model.VariablesPool;
import com.ansdoship.paronomasia.utils.FileUtils;
import com.ansdoship.paronomasia.view.Container;
import com.ansdoship.paronomasia.view.LocalTextView;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.util.ArrayList;
import java.util.Map;

public class UILoader {

    private final String TAG = "UILoader";

    private Context context;
    private VariablesPool variablesPool;

    private Container container;
    private String name;

    public UILoader(Context context,VariablesPool variablesPool){
        this.context = context;
        this.variablesPool = variablesPool;
        this.container = new Container(context, R.id.ui_pool);
    }

    public void load(){
        String configContent = FileUtils.readFileContent(PathManager.get("f_ui_config"));
        YamlReader reader = new YamlReader(configContent);
        Map map = null;
        try {
            map = (Map)reader.read();
        }catch (YamlException err){
            err.printStackTrace();
        }
        name = (String)map.get("name");
        ArrayList widgetList = (ArrayList)map.get("widgets");
        Log.i(TAG,"控件数量："+widgetList.size());
        for(int i = 0;i<widgetList.size();i++){
            Map widgetMap = (Map)widgetList.get(i);
            String type = ((String)widgetMap.get("type")).trim();
            String posX = ((String)widgetMap.get("x")).trim();
            String posY = ((String)widgetMap.get("y")).trim();
            if(type.equals("text")){
                String fontSize = (String)widgetMap.get("fontsize");
                String data = ((String)widgetMap.get("data")).trim();
                String color = (String)widgetMap.get("color");
                LocalTextView item = new LocalTextView(context);
                item.setTextSize(Float.parseFloat(fontSize));
                item.setTextColor(Color.parseColor(color));
                item.setDataBindName(data);
                container.addView(item,Integer.parseInt(posX),Integer.parseInt(posY));
            }
        }
    }

    public Container getContainer() {
        return container;
    }
}

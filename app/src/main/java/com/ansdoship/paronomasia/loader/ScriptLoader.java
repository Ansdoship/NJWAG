package com.ansdoship.paronomasia.loader;

import android.content.Context;
import android.util.Log;

import com.ansdoship.paronomasia.manager.PathManager;
import com.ansdoship.paronomasia.model.Chapter;
import com.ansdoship.paronomasia.model.ChoiceItem;
import com.ansdoship.paronomasia.model.Choices;
import com.ansdoship.paronomasia.model.ConditionItem;
import com.ansdoship.paronomasia.model.DataType;
import com.ansdoship.paronomasia.model.Dynamic;
import com.ansdoship.paronomasia.model.Function;
import com.ansdoship.paronomasia.model.FunctionPool;
import com.ansdoship.paronomasia.model.Game;
import com.ansdoship.paronomasia.model.Initializer;
import com.ansdoship.paronomasia.model.Stage;
import com.ansdoship.paronomasia.model.VariablesPool;
import com.ansdoship.paronomasia.utils.FileUtils;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ScriptLoader {

    private Context context;
    private String scriptContent;

    public ScriptLoader(Context context){
        this.context = context;
    }

    public Game load(){
        String scriptPath = PathManager.get("f_main_script");
        scriptContent = FileUtils.readFileContent(scriptPath);

        YamlReader reader = new YamlReader(scriptContent);
        System.out.println(scriptContent);

        Map map = null;
        try{
            map = (Map)reader.read();
        }catch (YamlException err){
            err.printStackTrace();
        }
        Game game = new Game();
        game.setTitle((String)map.get("title"));
        ArrayList variables =  (ArrayList) map.get("variables");
        VariablesPool variablesPool = new VariablesPool();
        for(int i = 0;i<variables.size();i++){
            variablesPool.put((String)variables.get(i),new DataType());
        }
        game.setVariables(variablesPool);

        Initializer initializer = new Initializer();
        Map initMap = (Map)map.get("init");
        ArrayList initActionList = (ArrayList)initMap.get("action");
        ArrayList initParamList = (ArrayList)initMap.get("param");
        FunctionPool initFunctionPool = new FunctionPool();
        for(int j = 0;j<initActionList.size();j++){
            initFunctionPool.add(new Function((String)initActionList.get(j),(String)initParamList.get(j)));
        }
        initializer.setFunctionPool(initFunctionPool);
        game.setInitializer(initializer);

        Stage stage = new Stage();
        Map chapters = (Map) map.get("stage");
        Iterator<Map.Entry<String, String>> it = chapters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            Map chapterMap = (Map)chapters.get(entry.getKey());
            Chapter chapter = new Chapter();
            chapter.setName(entry.getKey());
            chapter.setTitle((String)chapterMap.get("chapter"));
            chapter.setStory((String)chapterMap.get("story"));
            ArrayList choiceList = (ArrayList)chapterMap.get("choices");
            Choices choices = new Choices();
            for(int i = 0;i<choiceList.size();i++){
                Map choiceMap = (Map)choiceList.get(i);
                ChoiceItem choiceItem = new ChoiceItem();
                choiceItem.setKeyword((String)choiceMap.get("keyword"));
                choiceItem.setDescription((String)choiceMap.get("description"));
                ArrayList actionList = (ArrayList)choiceMap.get("action");
                ArrayList paramList = (ArrayList)choiceMap.get("param");
                FunctionPool functionPool = new FunctionPool();
                for(int j = 0;j<actionList.size();j++){
                    functionPool.add(new Function((String)actionList.get(j),(String)paramList.get(j)));
                }
                choiceItem.setFunctionPool(functionPool);
                choices.add(choiceItem);
            }
            chapter.setChoices(choices);
            stage.addChapter(chapter);
        }
        Dynamic dynamic = new Dynamic();
        ArrayList conditionList = (ArrayList)map.get("dynamic");
        for(int i = 0;i<conditionList.size();i++){
            ConditionItem item = new ConditionItem();
            Map conditionMap = (Map)conditionList.get(i);
            Map paramMap = (Map)conditionMap.get("condition");
            item.setChapterCondition((String)paramMap.get("chapter"));
            item.setExpressionCondition((String)paramMap.get("expression"));
            ArrayList actionList = (ArrayList)conditionMap.get("action");
            ArrayList paramList = (ArrayList)conditionMap.get("param");
            FunctionPool functionPool = new FunctionPool();
            for(int j = 0;j<actionList.size();j++){
                functionPool.add(new Function((String)actionList.get(j),(String)paramList.get(j)));
            }
            item.setFunctionPool(functionPool);
            dynamic.addCondition(item);
        }
        game.setStage(stage);
        game.setDynamicConditions(dynamic);
        return game;
    }

}

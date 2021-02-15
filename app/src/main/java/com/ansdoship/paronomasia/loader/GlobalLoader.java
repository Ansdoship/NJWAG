package com.ansdoship.paronomasia.loader;

import android.content.Context;
import android.content.SharedPreferences;

import com.ansdoship.paronomasia.manager.AssetsManager;
import com.ansdoship.paronomasia.manager.ChoiceManager;
import com.ansdoship.paronomasia.manager.DialogManager;
import com.ansdoship.paronomasia.manager.TitleManager;
import com.ansdoship.paronomasia.model.Game;

public class GlobalLoader {

    private static GlobalLoader instance = null;
    private Context context;
    private Game game;


    private ScriptLoader scriptLoader;
    private UILoader uiLoader;

    private GlobalLoader(Context context){
        this.context= context;
        scriptLoader = new ScriptLoader(context);
    }

    public static GlobalLoader getInstance(Context context){
        if(instance==null){
            instance = new GlobalLoader(context);
        }
        return instance;
    }

    public void init(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        int startNumber = sharedPreferences.getInt("startNumber",0);
        if(startNumber>0){
            sharedPreferences.edit()
                    .putInt("startNumber",startNumber+1)
                    .apply();
            //AssetsManager.getInstance(context).release();
            load();
        }else{
            sharedPreferences.edit()
                    .putInt("startNumber",1)
                    .apply();
            AssetsManager.getInstance(context).release();
            load();
        }
    }

    private void load(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                game = scriptLoader.load();
                uiLoader = new UILoader(context,game.getVariables());
                game.getVariables().setDataListener(uiLoader.getContainer());
                uiLoader.load();
                DialogManager.getInstance(GlobalLoader.this).load(game.getStage().getCurrentChapter().getStory());
                ChoiceManager.getInstance(GlobalLoader.this).load(game.getStage().getCurrentChapter().getChoices());
                TitleManager.getInstance(context).loadGlobalTitle(game.getStage().getCurrentChapter().getTitle());
                game.build();
            }
        }).start();
    }

    public void refresh(){
        game.getVariables().testPrint();
        DialogManager.getInstance(this).load(game.getStage().getCurrentChapter().getStory());
        ChoiceManager.getInstance(this).load(game.getStage().getCurrentChapter().getChoices());
        TitleManager.getInstance(context).loadGlobalTitle(game.getStage().getCurrentChapter().getTitle());
    }

    public Context getContext(){
        return context;
    }

    public Game getGame() {
        return game;
    }
}

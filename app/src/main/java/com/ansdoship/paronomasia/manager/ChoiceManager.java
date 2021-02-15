package com.ansdoship.paronomasia.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ansdoship.paronomasia.R;
import com.ansdoship.paronomasia.loader.GlobalLoader;
import com.ansdoship.paronomasia.model.ChoiceItem;
import com.ansdoship.paronomasia.model.Choices;
import com.ansdoship.paronomasia.model.Game;

public class ChoiceManager {

    private final String TAG = "ChoiceManager";

    private static ChoiceManager instance = null;
    private GlobalLoader globalLoader;
    private  Context context;
    private int[] idGroup = {R.id.choice_button_one,R.id.choice_button_two,R.id.choice_button_three,R.id.choice_button_four};
    private int buttonNumber = idGroup.length;
    private Button[] buttonGroup = new Button[buttonNumber];

    private ChoiceManager(GlobalLoader loader){
        this.globalLoader = loader;
        this.context = globalLoader.getContext();
        init(context);
    }

    public static ChoiceManager getInstance(GlobalLoader globalLoader){
        if(instance==null){
            instance = new ChoiceManager(globalLoader);
        }
        return instance;
    }

    private void init(Context context){
        Activity activity = (Activity)context;
        for (int i = 0;i<buttonNumber;i++){
            buttonGroup[i] = activity.findViewById(idGroup[i]);
            buttonGroup[i].setTag(i);
            buttonGroup[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Game game = globalLoader.getGame();
                    game.choiceTrigger((Integer)v.getTag());
                    globalLoader.refresh();
                }
            });
        }
    }

    public void load(final Choices choices){
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buttonGroup[0].setVisibility(View.GONE);
                buttonGroup[1].setVisibility(View.GONE);
                buttonGroup[2].setVisibility(View.GONE);
                buttonGroup[3].setVisibility(View.GONE);
                for (int i = 0;i<choices.size();i++){
                    ChoiceItem choiceItem = choices.get(i);
                    buttonGroup[i].setVisibility(View.VISIBLE);
                    buttonGroup[i].setText(choiceItem.getKeyword());
                }
            }
        });
    }
}

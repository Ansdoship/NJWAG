package com.ansdoship.paronomasia.manager;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.ansdoship.paronomasia.R;

import io.noties.markwon.Markwon;

public class TitleManager {

    private static TitleManager instance = null;
    private Markwon markwon;
    private Context context;

    private TextView globalTitleView;

    private TitleManager(Context context){
        this.context = context;
        this.markwon = Markwon.create(context);
        init();
    }

    public static TitleManager getInstance(Context context){
        if (instance == null) {
            instance = new TitleManager(context);
        }
        return instance;
    }

    private void init(){
        Activity activity = (Activity)context;
        globalTitleView = activity.findViewById(R.id.main_title);
    }

    public void loadGlobalTitle(final String content){
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                markwon.setMarkdown(globalTitleView, content);
            }
        });
    }
}

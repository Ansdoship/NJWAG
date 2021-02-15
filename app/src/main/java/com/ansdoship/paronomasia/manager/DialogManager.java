package com.ansdoship.paronomasia.manager;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.ansdoship.paronomasia.R;
import com.ansdoship.paronomasia.loader.GlobalLoader;

import io.noties.markwon.Markwon;

public class DialogManager {

    private static DialogManager instance = null;
    private GlobalLoader globalLoader;
    private Context context;
    private TextView dialogView;
    private Markwon markwon;

    private DialogManager(GlobalLoader loader) {
        this.globalLoader = loader;
        this.context = globalLoader.getContext();
        init(context);
    }

    public static DialogManager getInstance(GlobalLoader loader) {
        if (instance == null) {
            instance = new DialogManager(loader);
        }
        return instance;
    }

    private void init(final Context context) {
        Activity activity = (Activity) context;
        markwon = Markwon.create(context);
        dialogView = activity.findViewById(R.id.dialog_text);
    }

    public void load(final String content) {
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                markwon.setMarkdown(dialogView, content);
            }
        });
    }
}

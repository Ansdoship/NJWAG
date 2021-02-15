package com.ansdoship.paronomasia.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ansdoship.paronomasia.R;

import io.noties.markwon.Markwon;

public class InputDialog extends AlertDialog {

    private InputListener listener;

    private TextView textView;
    private EditText editText;
    private Button button;

    public InputDialog(Context context,String title,String okButton){
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_input,null,false);
        textView = view.findViewById(R.id.dialog_title);
        editText = view.findViewById(R.id.dialog_text);
        button = view.findViewById(R.id.dialog_button);
        Markwon markwon = Markwon.create(context);
        markwon.setMarkdown(textView,title);
        button.setText(okButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getEditableText().toString();
                listener.input(content);
            }
        });
        setCancelable(false);
        setContentView(view);
    }

    public void setListener(InputListener listener) {
        this.listener = listener;
    }
}

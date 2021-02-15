package com.ansdoship.paronomasia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ansdoship.paronomasia.loader.GlobalLoader;
import com.ansdoship.paronomasia.manager.DialogManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalLoader.getInstance(this).init();
    }
}

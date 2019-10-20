package com.smr.studios.tweeser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startSearchEngine(View view)
    {
        Intent intent = new Intent(this,SearchEngine.class);
        startActivity(intent);
    }
}

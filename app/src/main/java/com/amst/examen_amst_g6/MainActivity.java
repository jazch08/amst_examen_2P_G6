package com.amst.examen_amst_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goBatery(View view){
        Intent intent = new Intent(this, BateryActivity.class);
        startActivity(intent);
    }

    public void goData(View view){
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }


}
package com.example.nam78.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ADDActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }


    public void onBackButtonClicked(View v) {
        Toast.makeText((getApplicationContext()), "돌아가기.", Toast.LENGTH_LONG);
        finish();
    }
}
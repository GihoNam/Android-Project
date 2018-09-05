package com.example.nam78.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addClick (View v)
    {
        EditText Number1 = (EditText) findViewById(R.id.number);
        EditText Number2 = (EditText)  findViewById(R.id.number1);
        Intent intent = new Intent(this, ADDActivity.class);
        TextView result = (TextView) findViewById(R.id.result);
        int n1 = Integer.parseInt(Number1.getText().toString());
        int n2 = Integer.parseInt(Number2.getText().toString());
        result.setText(Integer.toString(n1 + n2));
       intent.putExtra("value", "문자열");
        startActivity(intent);
    }
    public void subtrackClick (View v)
    {
        EditText Number1 = (EditText) findViewById(R.id.number);
        EditText Number2 = (EditText) findViewById(R.id.number1);
        TextView result = (TextView) findViewById(R.id.result);
        int n1 = Integer.parseInt(Number1.getText().toString());
        int n2 = Integer.parseInt(Number2.getText().toString());
        result.setText(Integer.toString(n1 - n2));
    }
    public void multiplyClick (View v)
    {
        EditText Number1 = (EditText) findViewById(R.id.number);
        EditText Number2 = (EditText) findViewById(R.id.number1);
        TextView result = (TextView) findViewById(R.id.result);
        int n1 = Integer.parseInt(Number1.getText().toString());
        int n2 = Integer.parseInt(Number2.getText().toString());
        result.setText(Integer.toString(n1 * n2));
    }
    public void divideClick (View v)
    {
        EditText Number1 = (EditText) findViewById(R.id.number);
        EditText Number2 = (EditText) findViewById(R.id.number1);
        TextView result = (TextView) findViewById(R.id.result);
        int n1 = Integer.parseInt(Number1.getText().toString());
        int n2 = Integer.parseInt(Number2.getText().toString());
        result.setText(String.format("%.2f",Double.valueOf(n1/n2)));
    }
    public void onButtonClick3(View v)
    {
        Intent back = new Intent(getApplicationContext(), ADDActivity.class);
        startActivity(back);
    }
}

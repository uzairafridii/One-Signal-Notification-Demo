package com.uzair.onesignaldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyApplication myApplication;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myApplication = MyApplication.getInstance();

        sharedPreferences = getSharedPreferences("data" , MODE_PRIVATE);

     String name = sharedPreferences.getString("name", "defaulTName");
     String phone = sharedPreferences.getString("phone", "defaultPhone");

     TextView textView  = findViewById(R.id.result);

     textView.setText(name+"---"+phone);

    }
}

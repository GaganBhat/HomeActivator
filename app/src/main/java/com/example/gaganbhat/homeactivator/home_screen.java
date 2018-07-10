package com.example.gaganbhat.homeactivator;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class home_screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        TextView textViewCustom = (TextView) findViewById(R.id.textView);
        Typeface bebasNeue = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue Thin.otf");
        textViewCustom.setTypeface(bebasNeue);
        }
}

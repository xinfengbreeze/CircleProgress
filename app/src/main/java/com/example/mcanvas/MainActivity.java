package com.example.mcanvas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    RingProgressbar cp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cp = (RingProgressbar) findViewById(R.id.cp);
        cp.setUnit("分");
        cp.showNumberFlag(true);
        cp.setMaxValue(360);
        cp.setMinValue(0);
        cp.setBackColor(Color.BLACK);
        cp.setProgressValue(0);
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp.play(360);
            }
        });
    }
}

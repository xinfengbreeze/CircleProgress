package com.example.mcanvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CircleProgressbar cp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cp = (CircleProgressbar) findViewById(R.id.cp);
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp.play(0,360);
            }
        });
    }
}

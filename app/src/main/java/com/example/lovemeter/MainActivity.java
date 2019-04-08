package com.example.lovemeter;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b_compute;
    ImageView iv_meter, iv_needle;
    EditText et_yourName, et_otherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_compute = (Button) findViewById(R.id.b_compute);
        iv_meter = (ImageView) findViewById(R.id.iv_meter);
        iv_needle = (ImageView) findViewById(R.id.iv_needle);
        et_yourName = (EditText) findViewById(R.id.et_yourName);
        et_otherName = (EditText) findViewById(R.id.et_otherName);


        b_compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String yourName = et_yourName.getText().toString().toLowerCase();
                String otherName = et_otherName.getText().toString().toLowerCase();

                int totalLetters = yourName.length() + otherName.length();
                int totalMatches = 0;

                for (int i = 0; i < yourName.length(); i++){
                    for (int j = 0; j < otherName.length(); j++){
                        if(yourName.charAt(i) == otherName.charAt(j)){
                            totalMatches++;
                        }
                    }
                }

                for (int i = 0; i < otherName.length(); i++){
                    for (int j = 0; j < yourName.length(); j++){
                        if(otherName.charAt(i) == yourName.charAt(j)){
                            totalMatches++;
                        }
                    }
                }

                float compatScore = ((float) totalMatches )/ (float) totalLetters;
                int loveScore = ((int) (compatScore * 100)) - 50 ;



                RotateAnimation rotateDial = new RotateAnimation(0,360+ loveScore, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                rotateDial.setDuration(3000);
                rotateDial.setFillAfter(true);

                rotateDial.setInterpolator(new AccelerateDecelerateInterpolator());

                iv_needle.startAnimation(rotateDial);

                Toast.makeText(MainActivity.this, "Love score is: " + loveScore, Toast.LENGTH_SHORT).show();


            }
        });
    }
}

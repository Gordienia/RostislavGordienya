package com.example.rostislavgordienya;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    Button redButton, blueButton, greenButton;
    ColorsSecondScreen correctColor;
    int clicksToProceed;
    int clicked = 0;
    TextView hint;

    static final String[] colors = {"#F44336","#3F51B5","#4CAF50"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);
        hint = findViewById(R.id.howMuch);
        redButton = findViewById(R.id.redButton);
        blueButton = findViewById(R.id.blueButton);
        greenButton = findViewById(R.id.greenButton);
        redButton.setOnClickListener(redButtonListener);
        blueButton.setOnClickListener(blueButtonListener);
        greenButton.setOnClickListener(greenButtonListener);
        initScreen();
    }

    private void initScreen(){
        clicksToProceed = getRandomNumber();
        correctColor = getRandomColor();
        hint.setText(""+clicksToProceed);
        switch (correctColor){

            case RED:
                hint.setTextColor(Color.parseColor(colors[0]));
                break;
            case BLUE:
                hint.setTextColor(Color.parseColor(colors[1]));
                break;
            case GREEN:
                hint.setTextColor(Color.parseColor(colors[2]));
                break;
        }
    }

    private int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(20)+1;
    }

    private ColorsSecondScreen getRandomColor(){
        Random random = new Random();
        ColorsSecondScreen r_color = null;
        switch (random.nextInt(3)){
            case 0:
                r_color = ColorsSecondScreen.RED;
                break;
            case 1:
                r_color = ColorsSecondScreen.BLUE;
                break;
            case 2:
                r_color = ColorsSecondScreen.GREEN;
                break;
        }
        return r_color;
    }

    View.OnClickListener redButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(correctColor == ColorsSecondScreen.RED){
                clicked++;
                if(clicked == clicksToProceed){
                    toNextScreen();
                }
            }
        }
    };

    View.OnClickListener blueButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(correctColor == ColorsSecondScreen.BLUE){
                clicked++;
                if(clicked == clicksToProceed){
                    toNextScreen();
                }
            }
        }
    };

    View.OnClickListener greenButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(correctColor == ColorsSecondScreen.GREEN){
                clicked++;
                if(clicked == clicksToProceed){
                    toNextScreen();
                }
            }
        }
    };

    private void toNextScreen(){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
        finish();
    }
}

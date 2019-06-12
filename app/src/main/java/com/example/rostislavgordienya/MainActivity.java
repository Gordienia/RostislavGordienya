package com.example.rostislavgordienya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final String APP_PREFS_NAME = "prefs";

    int clicksToProceed;
    int clicked = 0;

    TextView hint;
    Button clickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hint = findViewById(R.id.howMuch);
        clickButton = findViewById(R.id.button);
        putStartTime();

        initScreen();
    }

    private void putStartTime(){
        SharedPreferences preferences = getSharedPreferences(APP_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Date date = new Date();
        String strDateFormat = "HH:mm:ss yyyy/MM/dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat, new Locale("ru","RU"));
        String formattedDate = dateFormat.format(date);

        editor.putString("start_time", formattedDate);
        editor.apply();
    }

    private void initScreen(){
        clicksToProceed = getRandomNumber();
        hint.setText(""+clicksToProceed);
        clickButton.setOnClickListener(buttonListener);
    }

    private int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(20)+1;
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clicked++;
            if(clicked == clicksToProceed){
                toNextScreen();
            }
        }
    };

    private void toNextScreen(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}

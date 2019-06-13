package com.example.rostislavgordienya;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LastScreen extends AppCompatActivity {

    static final String APP_PREFS_NAME = "prefs";
    TextView record, lastRecord;
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_screen);
        record = findViewById(R.id.now);
        lastRecord = findViewById(R.id.lastTime);
        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(LastScreen.this).setTitle("Уверены что хотите выйти?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                            }
                        })
                        .setMessage("Приложение будет закрыто.").create();
                dialog.show();
            }
        });
        calculateDiffInSeconds();
    }


    private void calculateDiffInSeconds(){
        SharedPreferences preferences = getSharedPreferences(APP_PREFS_NAME, MODE_PRIVATE);
        String startS = preferences.getString("start_time","");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd", new Locale("ru", "RU"));
        Date startDate = null;
        try {
            startDate = format.parse(startS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diffInS = (new Date().getTime() - startDate.getTime()) / 1000;


        String recordS = preferences.getString("record","");
        if(recordS != ""){
            record.setText("Прошли за: "+diffInS+" сек.");
            lastRecord.setText("В прошлый раз за: "+recordS+" сек.");
        } else {
            record.setText("Прошли за: "+diffInS+" сек.");
            lastRecord.setText("");
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("record", diffInS+"");
        editor.apply();
    }
}

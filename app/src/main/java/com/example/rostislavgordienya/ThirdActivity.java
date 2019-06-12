package com.example.rostislavgordienya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ThirdActivity extends AppCompatActivity {

    TextView hint;
    Button confirmButton;
    EditText wordInput;
    String correctWord;

    static final String[] words = {"биолог", "барьер", "гнездо", "единый", "забава", "мишень", "премия", "ретушь", "утрата", "экстаз"};
    static final int LETTERS_TO_REPLACE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_screen);
        hint = findViewById(R.id.hint);
        confirmButton = findViewById(R.id.confirmButton);
        wordInput = findViewById(R.id.word);
        initScreen();
    }

    private void initScreen(){
        hint.setText(genWordForGuess());
        confirmButton.setOnClickListener(confirmButtonListener);
    }

    private String genWordForGuess(){
        Random random = new Random();
        ArrayList<Integer> excluding = new ArrayList<>();
        String r_word = words[random.nextInt(words.length)];
        correctWord = r_word;
        char[] r_word_chars = r_word.toCharArray();
        int replaced = 0;
        while(replaced < LETTERS_TO_REPLACE){
            int r = random.nextInt(r_word_chars.length);
            if(excluding.contains(r)){
                continue;
            }
            excluding.add(r);
            r_word_chars[r] = '_';
            replaced++;
        }

        return String.valueOf(r_word_chars);
    }

    View.OnClickListener confirmButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String guess = wordInput.getText().toString().toLowerCase();
            if(guess.equals(correctWord)){
                toNextScreen();
            } else {
                wordInput.setText("");
            }
        }
    };


    private void toNextScreen(){
        Intent intent = new Intent(this, LastScreen.class);
        startActivity(intent);
        finish();
    }
}

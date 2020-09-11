//Noah Chiang
// ID: 216612285
// Youtube link :https://youtu.be/X7C5Nf2FDm4
package com.example.caps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.roumani.i2c.CountryDB;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();


    }

    private Game game = new Game();
    private String question;
    private String answer;
    private int score = 0;
    private int qNum = 1;



    private void start() {
        String[] poop = game.qa().split("\n", 2);
        question = poop[0];
        answer = poop[1];

        ((TextView) findViewById(R.id.question)).setText(question);
    }


    public void OnClick(View v) {
        if (qNum == 10) {


            finish();
            return;


        } else {
            String inputAnswer = ((EditText) findViewById(R.id.answer)).getText().toString().toUpperCase();


            if (inputAnswer.equalsIgnoreCase(this.answer)) {
                score++;
            }


            String logComposed = "Q# " + qNum + ":" + this.question + "\n" + "Your answer: " + inputAnswer + "\n" + "Correct answer: " + this.answer + "\n";
            TextView logBox = (TextView) findViewById(R.id.log);

            String history = logBox.getText().toString();
            logBox.setText(logComposed + "\n");
            logBox.append(history);
            ((TextView)findViewById(R.id.score)).setText("score ="+score);


            qNum++;
            ((TextView) findViewById(R.id.qNum)).setText("Q# " + qNum);

            if (qNum == 10) {
                ((TextView) findViewById(R.id.qNum)).setText("Game Over!");
                ((Button) findViewById(R.id.button)).setEnabled(false);

            } else {

                start();
            }
        }
    }
    }









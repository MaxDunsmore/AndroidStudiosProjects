package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView timer;
    TextView question;
    TextView score;
    TextView done;
    Button playAgain, button0, button1, button2, button3;
    GridLayout gridLayout;
    int locationOfCorrectAnswer;
    int wrongAnswer;
    int scoreTracker = 0;
    int numberOfQuestions = 0;
    CountDownTimer countDownTimer;
    Boolean timerRunning = false;
    String timerText;

    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            done.setText("Correct");
            scoreTracker++;
            newQuestion();
        } else {
            done.setText("Incorrect");
        }
        numberOfQuestions++;
        String scoreString = scoreTracker + "/" + numberOfQuestions;
        score.setText(scoreString);
    }

    public void newQuestion() {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        final int min = -9;
        final int max = 9;
        int correctAnswer = a + b;
        ArrayList<Integer> answers = new ArrayList<>();
        String questionString = a + " + " + b;
        question.setText(questionString);
        locationOfCorrectAnswer = random.nextInt(4);
        wrongAnswer = 0;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(correctAnswer);
            } else {
                wrongAnswer = random.nextInt(((max - min) + 1) + min) + correctAnswer;
                while (wrongAnswer == correctAnswer) {
                    wrongAnswer = random.nextInt(((max - min) + 1) + min) + correctAnswer;
                    for (int x = 0; x < answers.size(); x++) {
                        while (wrongAnswer == answers.get(x) || wrongAnswer == correctAnswer) {
                            wrongAnswer = random.nextInt(((max - min) + 1) + min) + correctAnswer;
                        }
                    }
                }
                answers.add(wrongAnswer);
            }
        }
        String buttonText0 = Integer.toString(answers.get(0));
        String buttonText1 = Integer.toString(answers.get(1));
        String buttonText2 = Integer.toString(answers.get(2));
        String buttonText3 = Integer.toString(answers.get(3));
        button0.setText(buttonText0);
        button1.setText(buttonText1);
        button2.setText(buttonText2);
        button3.setText(buttonText3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.start);
        timer = findViewById(R.id.timer);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        done = findViewById(R.id.done);
        startButton = findViewById(R.id.start);
        gridLayout = findViewById(R.id.gridLayout);
        playAgain = findViewById(R.id.playAgain);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        countDownTimer = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText = millisUntilFinished / 1000 + "s";
                timer.setText(timerText);
                timerRunning = true;
            }
            @Override
            public void onFinish() {
                String finishedText = "Finished !!!";
                done.setText(finishedText);
                timerText = "0s";
                timer.setText(timerText);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                playAgain.setVisibility(View.VISIBLE);
                timerRunning = false;
            }
        };
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runGame();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void runGame() {
        if (!timerRunning) {
            countDownTimer.start();
        }
        timer.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        done.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        newQuestion();
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button0.setEnabled(true);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                scoreTracker = 0;
                numberOfQuestions = 0;
                score.setText("0/0");
                timer.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                done.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.INVISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
            }
        });
    }
}

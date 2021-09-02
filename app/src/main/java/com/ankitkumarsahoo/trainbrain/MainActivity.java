package com.ankitkumarsahoo.trainbrain;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;



public class MainActivity extends AppCompatActivity {


    public Button startButton;
    public LinearLayout gameLayout;
    public TextView timerView;
    public TextView questionView;

    public Button button0;
    public Button button1;
    public Button button2;
    public Button button3;

    public TextView resultView;
    public TextView pointsView;

    public Button playAgainButton;


    public ArrayList<Integer> answers = new ArrayList<>();
    public int locationOfCorrectAnswer;
    public int x, y;
    public int score = 0;
    public int totalQuestions = 0;

    public void toGame(View view) {

        startButton.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);

        setTimer();
        generateQuestion();
        setPointsView();
    }


    public void setTimer() {

        new CountDownTimer(30000 + 100, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                timerView.setText((millisUntilFinished/1000) + "s");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {

                timerView.setText("0s");
                resultView.setText("You Get " + score + "/" + totalQuestions + " Questions Correct!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();

    }

    @SuppressLint("SetTextI18n")
    public void setQuestion() {

        Random rand = new Random();

        x = rand.nextInt(21);
        y = rand.nextInt(21);

        questionView.setText(x + " + " + y);

    }

    @SuppressLint("SetTextI18n")
    public void setAnswer() {

        Random rand = new Random();
        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(x + y);
            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == x + y) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view) {


        if (String.valueOf(view.getTag()).equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultView.setText("Correct!");
        } else {

            resultView.setText("Wrong!");
        }

        totalQuestions++;
        setPointsView();
        generateQuestion();
    }

    @SuppressLint("SetTextI18n")
    public void setPointsView() {

        pointsView.setText(score + "/" + totalQuestions);
    }

    public void generateQuestion() {

        setQuestion();
        setAnswer();
        answers.clear();
    }

    public void playAgain(View view) {

        score = 0;
        totalQuestions = 0;

        setTimer();
        generateQuestion();
        setPointsView();
        resultView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        gameLayout = findViewById(R.id.gameLayout);

        timerView = findViewById(R.id.timerView);

        questionView = findViewById(R.id.questionView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        resultView = findViewById(R.id.resultView);

        pointsView = findViewById(R.id.pointsView);

        playAgainButton = findViewById(R.id.playAgainButton);

    }
}
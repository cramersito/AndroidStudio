package com.example.quiz2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity3 extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private TextView rightWrong;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;
    private EditText playerName;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    MediaPlayer mp;
    private int score;
    private boolean answered;


    private long backPressedTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);

        textViewQuestion = findViewById(R.id.TextViewQuestion);
        textViewScore = findViewById(R.id.score);
        textViewQuestionCount = findViewById(R.id.questionCount);
        textViewCountDown = findViewById(R.id.timer);
        rbGroup = findViewById(R.id.radioAnswers);
        rb1 = findViewById(R.id.radioAnswer1);
        rb2 = findViewById(R.id.radioAnswer2);
        rb3 = findViewById(R.id.radioAnswer3);
        buttonConfirmNext = findViewById(R.id.BConfirm);




        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        if (savedInstanceState == null) {
            QuizDBAux dbHelper = new QuizDBAux(this);
            questionList = dbHelper.getAllQuestions();
            questionCountTotal = 10;
            Collections.shuffle(questionList);

            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = 10;
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity3.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();



        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            String prefAux = currentQuestion.getQuestion();

            if (questionCounter < questionCountTotal) {
                currentQuestion = questionList.get(questionCounter);

                if (prefAux.startsWith("AUDIO")){
                    int audioAux = 0;
                    if (prefAux.startsWith("AUDIO-A")) {
                        audioAux = R.raw.antes_muerta;
                    }
                    if (prefAux.startsWith("AUDIO-B")) {
                        audioAux = R.raw.christmas;
                    }
                    if (prefAux.startsWith("AUDIO-C")) {
                        audioAux = R.raw.dragon_ball_rap;
                    }
                    if (prefAux.startsWith("AUDIO-D")) {
                        audioAux = R.raw.europes_living;
                    }
                    if (prefAux.startsWith("AUDIO-E")) {
                        audioAux = R.raw.macarena;
                    }
                    if (prefAux.startsWith("AUDIO-F")) {
                        audioAux = R.raw.negro_y_azul;
                    }
                    if (prefAux.startsWith("AUDIO-G")) {
                        audioAux = R.raw.bed_of_roses;
                    }
                    if (prefAux.startsWith("AUDIO-H")) {
                        audioAux = R.raw.bring_me_to_life;
                    }
                    if (prefAux.startsWith("AUDIO-I")) {
                        audioAux = R.raw.ritmo_de_la_noche;
                    }
                    if (prefAux.startsWith("AUDIO-J")) {
                        audioAux = R.raw.space_odity;
                    }
                    if (mp != null) {
                        mp.reset();
                    }
                    mp = MediaPlayer.create(getApplicationContext(), audioAux);
                    mp.start();}}




            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getAns1());
            rb2.setText(currentQuestion.getAns2());
            rb3.setText(currentQuestion.getAns3());
            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getCorrectAnswer()) {
            score = score + 3;

            textViewScore.setText("Score: " + score);
        }




        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnswer()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {
        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        setContentView(R.layout.finish3);
        Button playagain = findViewById(R.id.playagain);
        TextView highscore = findViewById(R.id.finalScore);
        TextView highestscore= findViewById(R.id.highestScore);
        int highest= mypref.getInt("highestscore",0);
        if(highest>=score){
            highestscore.setText("Highest Score : "+ highest);
        }else{highestscore.setText("New Highest Score : " + score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highestscore",score);
            editor.commit();}


        highscore.setText("Score : " + score);


        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }




    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
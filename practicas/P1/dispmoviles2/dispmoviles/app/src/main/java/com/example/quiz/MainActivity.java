package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button play;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pantalla de inicio del juego
        setContentView(R.layout.start);
        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicializa la partida
                startQuiz();
            }
        });

    }

    private void startQuiz(){
        //pasar a Quiz()
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }
}

package com.example.quiz;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Quiz extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //variables de pantalla
    private TextView pregunta;
    private ProgressBar progressBar;
    public TextView score;
    private RadioGroup respuestas;
    private RadioButton respuesta1;
    private RadioButton respuesta2;
    private RadioButton respuesta3;
    private RadioButton respuesta4;
    private Button playagain;
    private Button comprobar;
    private TextView finalScore;
    public Spinner questionSpinner;
    String[] spinnerAns;

    //variables auxiliares
    private ColorStateList colorTexto;
    private Question preguntaActual;
    private int indice = 0;
    private int puntuacion;
    private int aux = indice + 1;
    public Question[] quizQuestions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pregunta = findViewById(R.id.text_question);
        progressBar = findViewById(R.id.progressBar);
        score = findViewById(R.id.score);
        respuestas = findViewById(R.id.answer_group);
        respuesta1 = findViewById(R.id.answer1);
        respuesta2 = findViewById(R.id.answer2);
        respuesta3 = findViewById(R.id.answer3);
        respuesta4 = findViewById(R.id.answer4);
        playagain = findViewById(R.id.playagain);
        comprobar = findViewById(R.id.check);
        finalScore = findViewById(R.id.finalScore);



        //colorTexto = respuesta1.getTextColors();

        //array de preguntas a contestar
        quizQuestions = Generator();
        //inicializar la primera pregunta
        pregunta.setText(quizQuestions[0].getQuesion());
        respuesta1.setText(quizQuestions[0].getAnswer1());
        respuesta2.setText(quizQuestions[0].getAnswer2());
        respuesta3.setText(quizQuestions[0].getAnswer3());
        respuesta4.setText(quizQuestions[0].getAnswer4());
        progressBar.setProgress(0);



        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indice < 3){
                    //si la pregunta está respondida, comprobar la respuesta
                    if (respuesta1.isChecked() || respuesta2.isChecked() || respuesta3.isChecked() || respuesta4.isChecked()){
                        checkAnswer(quizQuestions[indice]);
                        showNext(quizQuestions[aux]);
                    }
                    //si no, mostrar por pantalla que se debe seleccionar una respuesta
                    else{
                    Toast.makeText(Quiz.this, "Seleccione una respuesta", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    setContentView(R.layout.activity_main2);
                    if(questionSpinner.isSelected()){
                        checkAnswerSpinner(quizQuestions[indice]);
                        if (indice < 4){
                            showNextSpinner(quizQuestions[aux]);
                        }else{
                            finishQuiz();
                        }

                    }else{
                        Toast.makeText(Quiz.this, "Seleccione una respuesta", Toast.LENGTH_SHORT).show();

                    }
                 }
            }
        });
    }
    private void showNext(Question q){
        /*respuesta1.setTextColor(colorTexto);
        respuesta2.setTextColor(colorTexto);
        respuesta3.setTextColor(colorTexto);
        respuesta4.setTextColor(colorTexto);*/
        respuestas.clearCheck();

        if (indice < 3){
            //se pasa a la siguiente pregunta si no se ha llegado a 5
            indice++;
            aux++;
            pregunta.setText(q.getQuesion());
            respuesta1.setText(q.getAnswer1());
            respuesta2.setText(q.getAnswer2());
            respuesta3.setText(q.getAnswer3());
            respuesta4.setText(q.getAnswer4());

            //aumentar la progressBar
            progressBar.setProgress(indice);
            comprobar.setText("Comprobar");
        }else{
            comprobar.setText("Finalizar");
            comprobar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setContentView(R.layout.activity_main2);
                }
            });
        }
    }

    private void showNextSpinner(Question q){
        respuestas.clearCheck();

        if (indice < 5){
            //se pasa a la siguiente pregunta si no se ha llegado a 5
            indice++;
            aux++;
            pregunta.setText(q.getQuesion());
            spinnerAns[0]= q.getAnswer1();
            spinnerAns[1]= q.getAnswer2();
            spinnerAns[2]= q.getAnswer3();
            spinnerAns[3]= q.getAnswer4();

            //aumentar la progressBar
            progressBar.setProgress(indice);
            comprobar.setText("Comprobar");
        }else{
            comprobar.setText("Finalizar");
            comprobar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //finaliza la partida
                    finishQuiz();
                }
            });
        }
    }


    public void checkAnswer(Question p){
        //comprobamos si la respuesta es correcta
        RadioButton seleccion = findViewById(respuestas.getCheckedRadioButtonId());
        int respuesta = respuestas.indexOfChild(seleccion) + 1;

        //comprobamos si ha respondido bien o mal e incrementamos/disminuimos la puntación
        if (respuesta == p.getCorrectAnswer()){
            Toast.makeText(Quiz.this, "Respuesta Correcta", Toast.LENGTH_SHORT).show();
            puntuacion += 3;
            score.setText("Score: " + puntuacion);
        }else{
            Toast.makeText(Quiz.this, "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
            puntuacion -= 2;
            score.setText("Score: " + puntuacion);
        }
    }

    public void checkAnswerSpinner(Question p){
        //comprobamos si la respuesta es correcta
        int respuesta = questionSpinner.getSelectedItemPosition() + 1;

        //comprobamos si ha respondido bien o mal e incrementamos/disminuimos la puntación
        if (respuesta == p.getCorrectAnswer()){
            Toast.makeText(Quiz.this, "Respuesta Correcta", Toast.LENGTH_SHORT).show();
            puntuacion += 3;
            score.setText("Score: " + puntuacion);
        }else{
            Toast.makeText(Quiz.this, "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
            puntuacion -= 2;
            score.setText("Score: " + puntuacion);
        }
    }


    public void finishQuiz(){
        setContentView(R.layout.finish);
        //mostramos la puntuación final
        finalScore.setText("Score: " + score);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vuelve al inicio
                setContentView(R.layout.start);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public Question[] Generator() {

        Question preguntas[] = new Question[15];
        preguntas[0] = new Question("¿Cuál es el río más largo del mundo?",
                "Amazonas",
                "Nilo",
                "Congo",
                "Danubio",
                1);

        preguntas[1] = new Question("Cuál es el ave voladora más grande del mundo en la actualidad?",
                "Águila Imperial",
                "Avestruz",
                "Cóndor andino",
                "Gambusino",
                3);

        preguntas[2] = new Question("¿Cómo se llama el procedimiento de subir la bandera?",
                "Hizar",
                "Izar",
                "Izal",
                "Atizar",
                2);

        preguntas[3] = new Question("¿Qué instrumento tocaba Paco de Lucía?",
                "Cajón",
                "Saxofón",
                "Guitarra",
                "las Palmas",
                3);

        preguntas[4] = new Question("¿En qué país se usó la primera bomba atómica en combate?",
                "Alemania",
                "Italia",
                "Japón",
                "China",
                3);

        preguntas[5] = new Question("¿Quién es el protagonista de la película “Rocky”?",
                "Van Damme",
                "Silvester Stallone",
                "Steven Segal",
                "Rober De niro",
                2);

        preguntas[6] = new Question("La campana de Gauss está asociada a…",
                "Física",
                "Economía",
                "Estadística",
                "Cálculo de probabilidades",
                4);

        preguntas[7] = new Question("¿Cuál fue el primer metal que empleó el hombre?",
                "Oro",
                "Tungsteno",
                "Hierro",
                "Cobre",
                4);

        preguntas[8] = new Question("¿A qué país pertenecen los cariocas?",
                "Brasil",
                "Alpino",
                "Paraguay",
                "Bolivia",
                1);

        preguntas[9] = new Question(" ¿De qué estilo arquitectónico es la Catedral de Notre Dame en París?",
                "Neogótico",
                "Clásico",
                "Neorromántico",
                "Gótico",
                4);

        preguntas[10] = new Question(" ¿Quién es el autor de el Quijote?",
                "Risto Mejide",
                "Leopoldo Pérez, Clarín ",
                "Cervantes",
                "Miguel Delibes",
                3);

        preguntas[11] = new Question("¿Cuándo acabó la II Guerra Mundial?",
                "1968",
                "1940",
                "1945",
                "No acabó",
                3);

        preguntas[12] = new Question("¿Qué tipo de animal es la ballena?",
                "Pez",
                "Molusco",
                "Ovíparo",
                "Mamífero",
                4);

        preguntas[13] = new Question("¿Dónde originaron los juegos olímpicos?",
                "Grecia",
                "Nicaragua",
                "Birmania",
                "Murcia",
                1);

        preguntas[14] = new Question("¿Cómo se llama la capital de Mongolia?",
                "Nueva York",
                "Ulan Bator",
                "Mongolia",
                "laos",
                2);


        int x = (int) Math.random()*4;
        Question[] preguntasFinales = new Question[5];
        preguntasFinales[0]=preguntas[x];
        preguntasFinales[1]=preguntas[5];
        preguntasFinales[2]=preguntas[6];
        preguntasFinales[3]=preguntas[7];
        preguntasFinales[4]=preguntas[8];

        return preguntasFinales;
    }

}

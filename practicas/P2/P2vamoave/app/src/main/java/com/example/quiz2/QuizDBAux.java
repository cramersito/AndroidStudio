package com.example.quiz2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;


public class QuizDBAux extends SQLiteOpenHelper {
    private static final String DB_name = "SuperQuiz.db";
    private static final int DB_version = 1;

    private SQLiteDatabase db;

    public QuizDBAux(Context context){
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        this.db = db;

        //creamos la base para introducir las preguntas en la BBDD
        final String SQL_Crete_QuestionTable = "CREATE TABLE " +
                QuizContract.QuestionsTable.name + "( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.column0 + " TEXT, " +
                QuizContract.QuestionsTable.column1 + " TEXT, " +
                QuizContract.QuestionsTable.column2 + " TEXT, " +
                QuizContract.QuestionsTable.column3 + " TEXT, " +
                QuizContract.QuestionsTable.column4 + " INTEGER" +
                ")";

        db.execSQL(SQL_Crete_QuestionTable);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " +  QuizContract.QuestionsTable.name);
        onCreate(db);
    }

    private void fillQuestionTable(){
        //insertamos las preguntas en la BBDD
        Question q0 = new Question("¿Cuál es el río más largo del mundo?",
                "Amazonas",
                "Nilo",
                "Congo",
                1);
        addQuestion(q0);

        Question q1 = new Question("Cuál es el ave voladora más grande del mundo en la actualidad?",
                "Águila Imperial",
                "Avestruz",
                "Cóndor andino",
                3);
        addQuestion(q1);

        Question q2 = new Question("¿Cómo se llama el procedimiento de subir la bandera?",
                "Hizar",
                "Izar",
                "Izal",
                2);
        addQuestion(q2);

        Question q3 = new Question("¿Qué instrumento tocaba Paco de Lucía?",
                "Cajón",
                "Las Palmas",
                "Guitarra",
                3);
        addQuestion(q3);

        Question q4 = new Question("¿En qué país se usó la primera bomba atómica en combate?",
                "Alemania",
                "Italia",
                "Japón",
                3);
        addQuestion(q4);

        Question q5 = new Question("¿Quién es el protagonista de la película “Rocky”?",
                "Van Damme",
                "Silvester Stallone",
                "Steven Segal",
                2);
        addQuestion(q5);

        Question q6 = new Question("La campana de Gauss está asociada a…",
                "Física",
                "Estadística",
                "Cálculo de probabilidades",
                3);
        addQuestion(q6);

        Question q7 = new Question("¿Cuál fue el primer metal que empleó el hombre?",
                "Oro",
                "Hierro",
                "Cobre",
                3);
        addQuestion(q7);

        Question q8 = new Question("¿A qué país pertenecen los cariocas?",
                "Brasil",
                "Alpino",
                "Paraguay",
                1);
        addQuestion(q8);

        Question q9 = new Question(" ¿De qué estilo arquitectónico es la Catedral de Notre Dame en París?",
                "Neogótico",
                "Clásico",
                "Gótico",
                3);
        addQuestion(q9);

        Question q10 = new Question(" ¿Quién es el autor de el Quijote?",
                "Risto Mejide",
                "Leopoldo Pérez, Clarín ",
                "Cervantes",
                3);
        addQuestion(q10);

        Question q11 = new Question("¿Cuándo acabó la II Guerra Mundial?",
                "1968",
                "1945",
                "No acabó",
                2);
        addQuestion(q11);

        Question q12 = new Question("¿Qué tipo de animal es la ballena?",
                "Pez",
                "Molusco",
                "Mamífero",
                3);
        addQuestion(q12);

        Question q13 = new Question("¿Dónde originaron los juegos olímpicos?",
                "Grecia",
                "Nicaragua",
                "Birmania",
                1);
        addQuestion(q13);

        Question q14 = new Question("¿Cómo se llama la capital de Mongolia?",
                "Ulan Bator",
                "Mongolia",
                "laos",
                1);
        addQuestion(q14);

        Question qa0 = new Question("AUDIO-A" + "¿A qué artista pertenece esta canción?",
                "Maria Isabel",
                "La Hungara",
                "Picasso",
                1);
        addQuestion(qa0);

        Question qa1 = new Question("AUDIO-B" + "¿De qué época es típica esta canción?",
                "Semana Santa",
                "Navidad",
                "Los martes",
                2);
        addQuestion(qa1);

        Question qa2 = new Question("AUDIO-C" + "¿A qué serie está dedicada está canción?",
                "Los Serrano",
                "Juego de tronos",
                "Dragon Ball",
                3);
        addQuestion(qa2);

        Question qa3 = new Question("AUDIO-D" + "¿A qué continente se refiere la canción?",
                "América",
                "Oceanía",
                "Europa",
                4);
        addQuestion(qa3);

        Question qa4 = new Question("AUDIO-E" + "¿Para qué se utiliza el ritmo de esta canción?",
                "Reanimación cardio-vascular",
                "Radiografías nasales",
                "Hacer macarrones",
                1);
        addQuestion(qa4);

        Question qa5 = new Question("AUDIO-F" + "¿A qué colores hace referencia la canción?",
                "Azul y negro",
                "Rojo y amarillo",
                "Ningún color",
                1);
        addQuestion(qa5);

        Question qa6 = new Question("AUDIO-G" + "¿De qué flores habla esta canción?",
                "Margaritas",
                "Flores muertas",
                "Rosas",
                3);
        addQuestion(qa6);

        Question qa7 = new Question("AUDIO-H" + "¿De qué artista es la canción?",
                "Avril Lavinge",
                "Evanescene",
                "Dover",
                2);
        addQuestion(qa7);

        Question qa8 = new Question("AUDIO-I" + "¿A qué temporalidad se refiere la canción?",
                "La tarde",
                "La noche",
                "La hora de la siesta",
                2);
        addQuestion(qa8);

        Question qa9 = new Question("AUDIO-J" + "¿Quién canta esta canción?",
                "David Bowie",
                "Mi madre",
                "Freddie Mercury",
                1);
        addQuestion(qa9);

    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.column0, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.column1, question.getAns1());
        cv.put(QuizContract.QuestionsTable.column2, question.getAns2());
        cv.put(QuizContract.QuestionsTable.column3, question.getAns3());
        cv.put(QuizContract.QuestionsTable.column4, question.getCorrectAnswer());
        db.insert(QuizContract.QuestionsTable.name, null, cv);

    }


    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + QuizContract.QuestionsTable.name, null);

        if (c.moveToFirst()){
            do{
                Question q = new Question();
                q.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column0)));
                q.setAns1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column1)));
                q.setAns2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column2)));
                q.setAns3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column3)));
                q.setCorrectAnswer(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.column4)));


                questionList.add(q);
            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }

}

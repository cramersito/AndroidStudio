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
                QuizContract.QuestionsTable.column4 + " TEXT, " +
                QuizContract.QuestionsTable.column5 + " INTEGER" +
                QuizContract.QuestionsTable.columndifficulty + "TEXT" +
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
                "Danubio",
                1);
        addQuestion(q0);

        Question q1 = new Question("Cuál es el ave voladora más grande del mundo en la actualidad?",
                "Águila Imperial",
                "Avestruz",
                "Cóndor andino",
                "Gambusino",
                3);
        addQuestion(q1);

        Question q2 = new Question("¿Cómo se llama el procedimiento de subir la bandera?",
                "Hizar",
                "Izar",
                "Izal",
                "Atizar",
                2);
        addQuestion(q2);

        Question q3 = new Question("¿Qué instrumento tocaba Paco de Lucía?",
                "Cajón",
                "Saxofón",
                "Guitarra",
                "las Palmas",
                3);
        addQuestion(q3);

        Question q4 = new Question("¿En qué país se usó la primera bomba atómica en combate?",
                "Alemania",
                "Italia",
                "Japón",
                "China",
                3);
        addQuestion(q4);

        Question q5 = new Question("¿Quién es el protagonista de la película “Rocky”?",
                "Van Damme",
                "Silvester Stallone",
                "Steven Segal",
                "Rober De niro",
                2);
        addQuestion(q5);

        Question q6 = new Question("La campana de Gauss está asociada a…",
                "Física",
                "Economía",
                "Estadística",
                "Cálculo de probabilidades",
                4);
        addQuestion(q6);

        Question q7 = new Question("¿Cuál fue el primer metal que empleó el hombre?",
                "Oro",
                "Tungsteno",
                "Hierro",
                "Cobre",
                4);
        addQuestion(q7);

        Question q8 = new Question("¿A qué país pertenecen los cariocas?",
                "Brasil",
                "Alpino",
                "Paraguay",
                "Bolivia",
                1);
        addQuestion(q8);

        Question q9 = new Question(" ¿De qué estilo arquitectónico es la Catedral de Notre Dame en París?",
                "Neogótico",
                "Clásico",
                "Neorromántico",
                "Gótico",
                4);
        addQuestion(q9);

        Question q10 = new Question(" ¿Quién es el autor de el Quijote?",
                "Risto Mejide",
                "Leopoldo Pérez, Clarín ",
                "Cervantes",
                "Miguel Delibes",
                3);
        addQuestion(q10);

        Question q11 = new Question("¿Cuándo acabó la II Guerra Mundial?",
                "1968",
                "1940",
                "1945",
                "No acabó",
                3);
        addQuestion(q11);

        Question q12 = new Question("¿Qué tipo de animal es la ballena?",
                "Pez",
                "Molusco",
                "Ovíparo",
                "Mamífero",
                4);
        addQuestion(q12);

        Question q13 = new Question("¿Dónde originaron los juegos olímpicos?",
                "Grecia",
                "Nicaragua",
                "Birmania",
                "Murcia",
                1);
        addQuestion(q13);

        Question q14 = new Question("¿Cómo se llama la capital de Mongolia?",
                "Nueva York",
                "Ulan Bator",
                "Mongolia",
                "laos",
                2);
        addQuestion(q14);

        Question q15 = new Question("AUDIO-A" + "¿A qué artista pertenece esta canción?",
                "Maria Isabel",
                "La Hungara",
                "Las Ketchup",
                "Picasso",
                1);
        addQuestion(q15);

        Question q16 = new Question("AUDIO-B" + "¿De qué época es típica esta canción?",
                "Semana Santa",
                "Navidad",
                "Los martes",
                "Acción de Gracias",
                2);
        addQuestion(q16);

        Question q17 = new Question("AUDIO-C" + "¿A qué serie está dedicada está canción?",
                "Los Serrano",
                "Juego de tronos",
                "Dragon Ball",
                "Doraemon",
                3);
        addQuestion(q17);

        Question q18 = new Question("AUDIO-D" + "¿A qué continente se refiere la canción?",
                "América",
                "Oceanía",
                "Asia",
                "Europa",
                4);
        addQuestion(q18);

        Question q19 = new Question("AUDIO-E" + "¿Para qué se utiliza el ritmo de esta canción?",
                "Reanimación cardio-vascular",
                "Radiografías nasales",
                "Cirujías estéticas",
                "laos",
                1);
        addQuestion(q19);

        Question q20 = new Question("AUDIO-F" + "¿A qué colores hace referencia la canción?",
                "Azul y negro",
                "Rojo y amarillo",
                "Ningún color",
                "Rosa y rojo",
                1);
        addQuestion(q20);

        Question q21 = new Question("AUDIO-G" + "¿De qué flores habla esta canción?",
                "Margaritas",
                "Flores muertas",
                "Rosas",
                "Petunias",
                3);
        addQuestion(q21);

        Question q22 = new Question("AUDIO-H" + "¿De qué artista es la canción?",
                "Avril Lavinge",
                "Evanescene",
                "Dover",
                "Cualquiera",
                2);
        addQuestion(q22);

        Question q23 = new Question("AUDIO-I" + "¿A qué temporalidad se refiere la canción?",
                "La tarde",
                "La noche",
                "La mañana",
                "La hora de la siesta",
                2);
        addQuestion(q23);

        Question q24 = new Question("AUDIO-J" + "¿Quién canta esta canción?",
                "David Bowie",
                "Mi madre",
                "Freddie Mercury",
                "Nadie",
                1);
        addQuestion(q24);

    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.column0, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.column1, question.getAns1());
        cv.put(QuizContract.QuestionsTable.column2, question.getAns2());
        cv.put(QuizContract.QuestionsTable.column3, question.getAns3());
        cv.put(QuizContract.QuestionsTable.column4, question.getAns3());
        cv.put(QuizContract.QuestionsTable.column5, question.getCorrectAnswer());
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
                q.setAns4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column4)));
                q.setCorrectAnswer(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.column5)));


                questionList.add(q);
            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(String difficulty ){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery(" SELECT * FROM " + QuizContract.QuestionsTable.name + "Where" +QuizContract.QuestionsTable.columndifficulty + " = ? ", null);

        if (c.moveToFirst()){
            do{
                Question q = new Question();
                q.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column0)));
                q.setAns1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column1)));
                q.setAns2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column2)));
                q.setAns3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column3)));
                q.setAns4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.column4)));
                q.setCorrectAnswer(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.column5)));

                questionList.add(q);
            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}

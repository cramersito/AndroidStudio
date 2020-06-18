package com.example.quiz;

import android.provider.BaseColumns;

public final class Generator extends Question {


    //crea variables de clase Question, selecciona las 5 que se usarán durante cada partida y crea un array con ellas

    public Generator() {

        Question generador;
        {
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
                    "Cervantes", "Miguel Delibes",
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



            Question quizQuestions[] = new Question[5];
            int x = (int) Math.random()*4;
            quizQuestions[0]=preguntas[x];
            quizQuestions[1]=preguntas[5];
            quizQuestions[2]=preguntas[6];
            quizQuestions[3]=preguntas[7];
            quizQuestions[4]=preguntas[8];



    }






            }

        }









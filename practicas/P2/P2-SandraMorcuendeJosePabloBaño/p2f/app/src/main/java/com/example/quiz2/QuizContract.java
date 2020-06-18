package com.example.quiz2;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {
    }



    public static class QuestionsTable implements BaseColumns {
        public static final String name = "quizQuestions";
        public static final String column0 = "question";
        public static final String column1 = "ans1";
        public static final String column2 = "ans2";
        public static final String column3 = "ans3";
        public static final String column4 = "ans4";
        public static final String column5 = "correctAnswer";
        public static final String columndifficulty="difficulty";
    }
}
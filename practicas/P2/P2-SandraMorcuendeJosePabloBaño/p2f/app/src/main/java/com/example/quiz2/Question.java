package com.example.quiz2;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private int correctAnswer;


    public Question() {
    }

    public Question(String question, String ans1, String ans2, String ans3, String ans4, int correctAnswer) {
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.correctAnswer = correctAnswer;

    }

    protected Question(Parcel in) {
        question = in.readString();
        ans1 = in.readString();
        ans2 = in.readString();
        ans3 = in.readString();
        ans4 = in.readString();
        correctAnswer = in.readInt();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(ans1);
        dest.writeString(ans2);
        dest.writeString(ans3);
        dest.writeString(ans4);
        dest.writeInt(correctAnswer);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public int getCorrectAnswer() { return correctAnswer; }

    public void setCorrectAnswer(int correctAnswer) { this.correctAnswer = correctAnswer;}}
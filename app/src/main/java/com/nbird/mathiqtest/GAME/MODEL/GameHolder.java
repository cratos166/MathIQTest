package com.nbird.mathiqtest.GAME.MODEL;

public class GameHolder {

    String hint1;
    String hint2;
    String hint3;
    String correctAnswer;

    int image;

    public GameHolder() {
    }

    public GameHolder(String hint1, String hint2, String hint3, String correctAnswer,int image) {
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.hint3 = hint3;
        this.correctAnswer = correctAnswer;
        this.image=image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHint1() {
        return hint1;
    }

    public void setHint1(String hint1) {
        this.hint1 = hint1;
    }

    public String getHint2() {
        return hint2;
    }

    public void setHint2(String hint2) {
        this.hint2 = hint2;
    }

    public String getHint3() {
        return hint3;
    }

    public void setHint3(String hint3) {
        this.hint3 = hint3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

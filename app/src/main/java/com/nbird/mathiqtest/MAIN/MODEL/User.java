package com.nbird.mathiqtest.MAIN.MODEL;

public class User {

    String name;
    int imageURL;
    int score;

    String myUID;

    public User() {
    }

    public User(String name, int imageURL, int score,String myUID) {
        this.name = name;
        this.imageURL = imageURL;
        this.score = score;
        this.myUID=myUID;
    }

    public String getMyUID() {
        return myUID;
    }

    public void setMyUID(String myUID) {
        this.myUID = myUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

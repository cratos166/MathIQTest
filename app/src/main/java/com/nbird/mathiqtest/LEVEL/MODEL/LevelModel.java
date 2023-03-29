package com.nbird.mathiqtest.LEVEL.MODEL;

public class LevelModel {
    String name;
    int imageURL;
    int level;

    public LevelModel(String name, int imageURL, int level) {
        this.name = name;
        this.imageURL = imageURL;
        this.level = level;
    }

    public LevelModel() {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

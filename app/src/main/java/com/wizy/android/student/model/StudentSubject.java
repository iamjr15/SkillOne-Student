package com.wizy.android.student.model;


import androidx.annotation.DrawableRes;

//Just Helper class to show items in hobbies recycler
public class StudentSubject {
    private String name;
    private String colorString;
    @DrawableRes
    private int image;
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorString() {
        return colorString;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
    }
}

package com.wizy.android.student.model;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private String number;
    private String password;
    private Gender gender;
    private Standard standard;
    private List<Subject> favSubjects;
    private List<Subject> leastFavSubjects;
    private List<Hobby> hobbies;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public List<Subject> getLeastFavSubjects() {
        return leastFavSubjects;
    }

    public void setLeastFavSubjects(List<Subject> leastFavSubjects) {
        this.leastFavSubjects = leastFavSubjects;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Subject> getFavSubjects() {
        return favSubjects;
    }

    public void setFavSubjects(List<Subject> favSubjects) {
        this.favSubjects = favSubjects;
    }

    public enum Gender {
        BOY, GIRL
    }

    public enum Standard {
        FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE
    }

    public enum Subject {
        SCIENCE, ENGLISH, MATHS, SOCIAL_STUDIES, LANGUAGES, COMPUTER_SCIENCE
    }

    public enum Hobby {
        GUITAR, PAINTING, MARTIAL_ARTS, DRUM_AND_PERCUSSION, KEYBOARD, DANCE
    }
}

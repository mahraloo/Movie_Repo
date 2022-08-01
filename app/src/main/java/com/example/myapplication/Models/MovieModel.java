package com.example.myapplication.Models;

public class MovieModel {
    private int id;
    private String title;
    private String image;
    private String director;
    private String year;
    private String rate;
    private String duration;
    private String introduction;

    public MovieModel(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public MovieModel(int id, String title, String image, String director, String year, String rate, String duration, String introduction) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.director = director;
        this.year = year;
        this.rate = rate;
        this.duration = duration;
        this.introduction = introduction;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public MovieModel() {
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}
}

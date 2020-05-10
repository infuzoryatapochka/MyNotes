package com.example.mynotes.models;

public class Note {
    private int id;
    private String title;
    private String body;

    public Note(){}

    public Note(int id,String title,String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Note(String title,String body){
        this.title = title;
        this.body = body;
    }

    public int id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String body() {
        return body;
    }

    public void id(int id) {
        this.id = id;
    }

    public void title(String title) {
        this.title = title;
    }

    public void body(String body) {
        this.body = body;
    }
}
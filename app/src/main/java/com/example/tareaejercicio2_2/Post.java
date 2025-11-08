package com.example.tareaejercicio2_2;

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    @Override
    public String toString() {
        return id + ". " + title;
    }
}

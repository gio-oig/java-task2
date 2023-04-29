package com.example.assignment.models;

import java.util.Date;

public class Post {
    public int id;
    public String title;
    public String content;
    public String author;
    public Date createdAt;
    public Date updatedAt;

    public Post() {}

    public Post(String title, String author, String content) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public Post(int id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
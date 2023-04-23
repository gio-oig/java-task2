package com.example.assignment.dao;

import com.example.assignment.models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    private Connection connection;

    public PostDao() {
        String url = "jdbc:mysql://localhost:3306/jvtask2?useSSL=false";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM posts");

            while (resultSet.next()) {
                Post post = new Post();
                post.id = resultSet.getInt("id");
                post.title = resultSet.getString("title");
                post.content = resultSet.getString("content");
                post.author = resultSet.getString("author");
                post.createdAt = resultSet.getTimestamp("created_at");
                post.updatedAt = resultSet.getTimestamp("updated_at");
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public void addPost(Post post) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO posts (title, content, author, created_at) VALUES (?, ?, ?, ?)");
            statement.setString(1, post.title);
            statement.setString(2, post.content);
            statement.setString(3, post.author);
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
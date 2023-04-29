package com.example.assignment;

import com.example.assignment.dao.PostDao;
import com.example.assignment.models.Post;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.sql.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "postServlet", value = "/post-servlet")
public class PostServlet extends HttpServlet {
    PostDao postDao = new PostDao();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the post parameters from the request
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");

        // Insert the post into the database
        try {
            postDao.addPost(new Post(title, author, content));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect the user back to the main page
        response.sendRedirect(request.getContextPath() + "/post-servlet");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve all posts from the database
        String html = "";
        try {
            List<Post> posts = postDao.getAllPosts();
            // Build the HTML table of posts

            html += "<style>";
            html += "table { font-family: arial, sans-serif; border-collapse: collapse; width: 100%; }";
            html += "th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }";
            html += "tr:nth-child(even) { background-color: #f2f2f2; }";
            html += "</style>";

            html += "<table>";
            html += "<tr><th>Title</th><th>Author</th><th>Content</th><th colspan=\"2\">Actions</th></tr>";

            for(Post post : posts) {
                html += "<tr>";
                html += "<td>" + post.title + "</td>";
                html += "<td>" + post.author + "</td>";
                html += "<td>" + post.content + "</td>";
                html += "<td><a href='"+ request.getContextPath() + "/update-post-servlet?id=" + post.id +"'>Update</a></td>";
                html += "<td>";
                html += "<form method='POST' action='" + request.getContextPath() + "/delete-post-servlet'>";
                html += "<input type='hidden' name='id' value='" + post.id + "'>";
                html += "<button>Delete</button>";
                html += "</form>";
                html += "</td>";
                html += "</tr>";
            }

            html += "</table>";
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Render the HTML page with the posts table
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Posts</title></head><body>");
        out.println("<h1>All Posts</h1>");
        out.println(html);
        out.println("</br>");
        out.println("<a href=\"" + request.getContextPath() + "\">Return to Create Form</a>");
        out.println("</body></html>");
    }
}

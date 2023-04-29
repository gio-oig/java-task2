package com.example.assignment;

import com.example.assignment.dao.PostDao;
import com.example.assignment.models.Post;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updatePostServlet", value = "/update-post-servlet")
public class updatePostServlet extends HttpServlet {

    PostDao postDao = new PostDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("id"));
        Post post = postDao.getById(postId);

        String html = "<form method='post' action='"+ request.getContextPath() + "/update-post-servlet'>";
        html += "<input type='hidden' name='id' value='" + post.id + "'><br>";
        html += "Title: <input type='text' name='title' value='" + post.title + "'><br>";
        html += "Author: <input type='text' name='author' value='" + post.author + "'><br>";
        html += "Content: <br><textarea name='content'>" + post.content + "</textarea><br>";
        html += "<input type='submit' value='Update Post'>";
        html += "</form>";

        // Send the HTML response to the client
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Update Post</title></head><body>");
        out.println("<h1>Update Post</h1>");
        out.println(html);
        out.println("</body></html>");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");

        try {
            postDao.updatePost(new Post(postId, title, author, content));
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/post-servlet");
    }
}

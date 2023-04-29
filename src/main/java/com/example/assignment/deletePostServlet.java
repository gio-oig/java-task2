package com.example.assignment;

import com.example.assignment.dao.PostDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "deletePostServlet", value = "/delete-post-servlet")
public class deletePostServlet extends HttpServlet {

    PostDao postDao = new PostDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the post ID parameter from the request
        int postId = Integer.parseInt(req.getParameter("id"));

        // Delete the post from the database
        try {
            postDao.deletePost(postId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect the user back to the main page
        resp.sendRedirect(req.getContextPath() + "/post-servlet");
    }
}

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h2>Add a New Post</h2>
    <form method="post" action="<%=request.getContextPath()%>/post-servlet">
        Title: <input type="text" name="title"><br>
        Author: <input type="text" name="author"><br>
        Content: <br><textarea name="content"></textarea><br>
        <input type="submit" value="Add Post">
    </form>
<br/>

<a href="<%=request.getContextPath()%>/post-servlet">show posts</a>
</body>
</html>


<%--<%--%>
<%--    int movie_id = Integer.parseInt(request.getParameter("movie_id"));--%>
<%--    Movie movie = Movie.read(movie_id);--%>
<%--    if (movie != null) { %>--%>
<%--<p><strong>Movie ID:</strong> <%= movie.getMovie_id() %></p>--%>
<%--<p><strong>Title:</strong> <%= movie.getTitle() %></p>--%>
<%--<p><strong>Director:</strong> <%= movie.getDirector() %></p>--%>
<%--<p><strong>Release Date:</strong> <%= movie.getRelease_date() %></p>--%>
<%--<p><strong>Rating:</strong> <%= movie.getRating() %></p>--%>
<%--<p><strong>Description:</strong> <%= movie.getDescription() %></p>--%>
<%--<% } else { %>--%>
<%--<p>Movie not found.</p>--%>
<%--<% } %>--%>
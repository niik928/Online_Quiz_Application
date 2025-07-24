<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.quizz.entities.Quizz" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quiz List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 30px;
            color: #333;
        }

        h2 {
            color: #2c3e50;
            margin-bottom: 20px;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            background-color: #ffffff;
            margin-bottom: 10px;
            padding: 15px 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        .admin-actions {
            margin-left: 15px;
            font-size: 0.9em;
        }

        .admin-actions a {
            color: #dc3545;
            margin-left: 10px;
        }

        .admin-actions a:first-child {
            color: #28a745;
        }
    </style>
</head>
<body>
    <h2>List of All Quizzes</h2>
    <ul>
        <%
            String role = (String) session.getAttribute("role");
            List<Quizz> quizzes = (List<Quizz>) request.getAttribute("quizzes");
            if (quizzes != null && !quizzes.isEmpty()) {
                for (Quizz quiz : quizzes) {
        %>
            <li>
                <a href="startquiz?id=<%= quiz.getId() %>"><%= quiz.getTitle() %></a>: 
                <%= quiz.getDescription() %>

                <% if ("admin".equals(role)) { %>
                    <span class="admin-actions">
                        <a href="editQuiz?id=<%= quiz.getId() %>">Edit</a>
                        <a href="deleteQuiz?id=<%= quiz.getId() %>">Delete</a>
                    </span>
                <% } %>
            </li>
        <%
                }
            } else {
        %>
            <li>No quizzes found.</li>
        <%
            }
        %>
    </ul>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quizz.entities.Quizz" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Delete Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
        }
        table {
            border-collapse: collapse;
            width: 70%;
            margin: auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        a.delete-link {
            color: #d9534f;
            font-weight: bold;
            text-decoration: none;
        }
        a.delete-link:hover {
            text-decoration: underline;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <c:forEach var="quiz" items="${quizzes}">
    <tr>
        <td>${quiz.id}</td>
        <td>${quiz.title}</td>
        <td>${quiz.description}</td>
        <td>
            <c:if test="${sessionScope.role == 'admin'}">
                <a class="delete-link" href="deleteQuiz?id=${quiz.id}" onclick="return confirm('Are you sure?');">Delete</a>
            </c:if>
            <c:if test="${sessionScope.role != 'admin'}">
                <span style="color:gray;">No action</span>
            </c:if>
        </td>
    </tr>
</c:forEach>



</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Start Quiz</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin-top: 50px;
        }

        form {
            background: white;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 0 10px #ccc;
            width: 300px;
            text-align: left;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <p>Quiz ID: ${quiz.id}</p>
    <h2><c:out value="${quiz.title}" /></h2>
    <p><c:out value="${quiz.description}" /></p>

    <form action="quizrun" method="GET">
       <input type="hidden" name="id" value="${quiz.id}" />
        <input type="submit" value="Start Quiz" />
    </form>

</body>
</html>

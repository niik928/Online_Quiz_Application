<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add Questions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
            background: #f9f9f9;
        }
        form {
            background: white;
            padding: 20px;
            max-width: 500px;
            border-radius: 8px;
            box-shadow: 0 0 10px #ccc;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #aaa;
        }
        input[type="submit"] {
            margin-top: 20px;
            background: #007bff;
            color: white;
            border: none;
            padding: 10px 25px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }
        input[type="submit"]:hover {
            background: #0056b3;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <h2>Add Question to Quiz</h2>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <!-- Get quizId from request parameter -->
    <c:set var="quizId" value="${param.quizId}" />

    <form method="post" action="addquestions">
        <input type="hidden" name="quizId" value="${quizId}" />

        <label for="questionText">Question:</label>
        <textarea id="questionText" name="questionText" required rows="4"></textarea>

        <label for="option1">Option 1:</label>
        <input type="text" id="option1" name="option1" required />

        <label for="option2">Option 2:</label>
        <input type="text" id="option2" name="option2" required />

        <label for="option3">Option 3:</label>
        <input type="text" id="option3" name="option3" required />

        <label for="option4">Option 4:</label>
        <input type="text" id="option4" name="option4" required />

        <label for="correctOption">Correct Option (1-4):</label>
        <input type="text" id="correctOption" name="correctOption" required pattern="[1-4]" />

        <input type="submit" value="Add Question" />
    </form>

    <a href="admin.html">Back to Admin Panel</a>

</body>
</html>

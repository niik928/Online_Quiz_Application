<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quiz Result</title>
    <style>
        body {
            font-family: Arial;
            background: #f0f0f0;
            padding: 30px;
        }

        .question {
            background: white;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }

        .correct {
            color: green;
        }

        .incorrect {
            color: red;
        }
       h1{
        text-align: center;
            color: #333;
       }
        h2 {
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>
<h1>Quiz Answer</h1>
<h2>Your Score: ${score} / ${total}</h2>

<c:forEach var="q" items="${questions}">
    <div class="question">
        <p><strong>Q:</strong> ${q.questionText}</p>
        <p>
            <strong>Your Answer:</strong>
            <c:choose>
                <c:when test="${userAnswers[q.id] == q.correctOption}">
                    <span class="correct">✅ ${userAnswers[q.id]}</span>
                </c:when>
                <c:otherwise>
                    <span class="incorrect">❌ ${userAnswers[q.id]}</span>
                    <br/>
                    <strong>Correct Answer:</strong> ${q.correctOption}
                </c:otherwise>
            </c:choose>
        </p>
    </div>
</c:forEach>

</body>
</html>

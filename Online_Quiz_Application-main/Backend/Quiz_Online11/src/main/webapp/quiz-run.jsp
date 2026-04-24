<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Start Quiz</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            padding: 20px;
            background: #f0f4f8;
            color: #333;
        }

        .question {
            margin-bottom: 25px;
            padding: 20px;
            background: #ffffff;
            border: 1px solid #dcdcdc;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
        }

        ul {
            list-style: none;
            padding-left: 0;
        }

        li {
            margin-bottom: 12px;
        }

        label {
            cursor: pointer;
            display: flex;
            align-items: center;
            font-size: 16px;
        }

        input[type="radio"] {
            margin-right: 12px;
        }

        button, input[type="submit"] {
            padding: 10px 20px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button {
            background-color: #007BFF;
            color: white;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
        }

        .hidden {
            display: none;
        }

    </style>
</head>
<body>

<h2 style="text-align:center;">Start Quiz</h2>

<form method="post" action="submitQuiz" id="quizForm">

    <c:forEach var="q" items="${questions}" varStatus="status">
        <div id="question_${status.index}" class="question ${status.index != 0 ? 'hidden' : ''}">
            <p><strong>Q${status.index + 1}:</strong> ${q.questionText}</p>
            <ul>
                <li><label><input type="radio" name="answer_${q.id}" value="A"> A. ${q.optionA}</label></li>
                <li><label><input type="radio" name="answer_${q.id}" value="B"> B. ${q.optionB}</label></li>
                <li><label><input type="radio" name="answer_${q.id}" value="C"> C. ${q.optionC}</label></li>
                <li><label><input type="radio" name="answer_${q.id}" value="D"> D. ${q.optionD}</label></li>
            </ul>
        </div>
    </c:forEach>

    <div style="text-align:center;">
        <button type="button" id="prevBtn" onclick="changeQuestion(-1)" disabled>Previous</button>
        <button type="button" id="nextBtn" onclick="changeQuestion(1)">Next</button>
        <input type="submit" id="submitBtn" class="hidden" value="Submit" onclick="return validateAllAnswered();" />
    </div>

</form>

<script>
    const questionIds = [
        <c:forEach var="q" items="${questions}" varStatus="s">
            "${q.id}"<c:if test="${!s.last}">,</c:if>
        </c:forEach>
    ];

    let currentQuestion = 0;
    const totalQuestions = questionIds.length;
    const userAnswers = {};

    function changeQuestion(step) {
        saveCurrentAnswer();
        document.getElementById('question_' + currentQuestion).classList.add('hidden');
        currentQuestion += step;
        document.getElementById('question_' + currentQuestion).classList.remove('hidden');

        document.getElementById('prevBtn').disabled = currentQuestion === 0;
        document.getElementById('nextBtn').classList.toggle('hidden', currentQuestion === totalQuestions - 1);
        document.getElementById('submitBtn').classList.toggle('hidden', currentQuestion !== totalQuestions - 1);

        restoreAnswer();
    }

    function saveCurrentAnswer() {
        const qid = questionIds[currentQuestion];
        const radios = document.getElementsByName('answer_' + qid);
        radios.forEach(r => {
            if (r.checked) {
                userAnswers[qid] = r.value;
            }
        });
    }

    function restoreAnswer() {
        const qid = questionIds[currentQuestion];
        const radios = document.getElementsByName('answer_' + qid);
        radios.forEach(r => {
            r.checked = userAnswers[qid] === r.value;
        });
    }

    function validateAllAnswered() {
        saveCurrentAnswer();
        for (let i = 0; i < totalQuestions; i++) {
            const qid = questionIds[i];
            if (!userAnswers[qid]) {
                alert('Please answer question ' + (i + 1));
                currentQuestion = i;
                document.querySelectorAll('[id^="question_"]').forEach(div => div.classList.add('hidden'));
                document.getElementById('question_' + i).classList.remove('hidden');
                document.getElementById('prevBtn').disabled = i === 0;
                document.getElementById('nextBtn').classList.toggle('hidden', i === totalQuestions - 1);
                document.getElementById('submitBtn').classList.toggle('hidden', i !== totalQuestions - 1);
                return false;
            }
        }

        for (const qid in userAnswers) {
            let hidden = document.createElement('input');
            hidden.type = 'hidden';
            hidden.name = 'answer_' + qid;
            hidden.value = userAnswers[qid];
            document.getElementById('quizForm').appendChild(hidden);
        }

        return true;
    }
</script>

</body>
</html>

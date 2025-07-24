package com.quizz.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizz.daos.QuestionDao;
import com.quizz.entities.Question;

@WebServlet("/submitQuiz")
public class SubmitQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); 
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            
            QuestionDao questionDao = new QuestionDao();
            List<Question> questions = questionDao.getAllQuestions();

            
            Map<Integer, String> userAnswers = new HashMap<>();
            int score = 0;

            
            for (Question q : questions) {
                String userAnswer = req.getParameter("answer_" + q.getId()); 

                if (userAnswer != null) {
                    userAnswer = userAnswer.trim();
                    userAnswers.put(q.getId(), userAnswer); 

                    String correctAnswer = q.getCorrectOption(); 
                    if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                        score++;
                    }
                }
            }

            
            req.setAttribute("questions", questions);
            req.setAttribute("userAnswers", userAnswers);
            req.setAttribute("score", score);
            req.setAttribute("total", questions.size());

          
            RequestDispatcher dispatcher = req.getRequestDispatcher("submit-quiz.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Database error occurred while processing the quiz.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "An unexpected error occurred.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}

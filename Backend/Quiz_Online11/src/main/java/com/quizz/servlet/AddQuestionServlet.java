package com.quizz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizz.daos.QuestionDao;
import com.quizz.entities.Question;

@WebServlet("/addquestions")
public class AddQuestionServlet extends HttpServlet {

    private QuestionDao questionDao;

    @Override
    public void init() throws ServletException {
        try {
            questionDao = new QuestionDao();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize QuestionDao", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

       
        String quizIdStr = req.getParameter("quizId");
        String questionText = req.getParameter("questionText");
        String option1 = req.getParameter("option1");
        String option2 = req.getParameter("option2");
        String option3 = req.getParameter("option3");
        String option4 = req.getParameter("option4");
        String correctOptionStr = req.getParameter("correctOption");

        try {
            int quizId = Integer.parseInt(quizIdStr);
            int correctOption = Integer.parseInt(correctOptionStr);

            // Basic validation
            if (questionText == null || questionText.trim().isEmpty()) {
                req.setAttribute("error", "Question text is required.");
                req.getRequestDispatcher("addquestions.jsp?quizId=" + quizId).forward(req, resp);
                return;
            }

            if (correctOption < 1 || correctOption > 4) {
                req.setAttribute("error", "Correct option must be between 1 and 4.");
                req.getRequestDispatcher("addquestions.jsp?quizId=" + quizId).forward(req, resp);
                return;
            }

            // Create question object
            Question question = new Question();
            question.setQuizId(quizId);
            question.setQuestionText(questionText);
            question.setOptionA(option1);
            question.setOptionB(option2);
            question.setOptionC(option3);
            question.setOptionD(option4);
            question.setCorrectOption(correctOptionStr); // Keep it String if your DB column is VARCHAR

            // Add to DB
            boolean success = questionDao.addQuestion(question);

            if (success) {
                resp.sendRedirect("addquestions.jsp?quizId=" + quizId);
            } else {
                req.setAttribute("error", "Failed to add question.");
                req.getRequestDispatcher("addquestions.jsp?quizId=" + quizId).forward(req, resp);
            }

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number format for quiz ID or correct option.");
            req.getRequestDispatcher("addquestions.jsp?quizId=" + quizIdStr).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("addquestions.jsp?quizId=" + quizIdStr).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       
        doPost(req, resp);
    }
}

package com.quizz.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizz.daos.QuizDao;
import com.quizz.entities.Quizz;

@WebServlet("/editQuiz")
public class EditQuizz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quizId = req.getParameter("id");

        if (quizId == null || quizId.isEmpty()) {
            req.setAttribute("error", "Quiz ID is missing.");
            req.getRequestDispatcher("/edit-quiz.jsp").forward(req, resp);
            return;
        }

        try {
            int id = Integer.parseInt(quizId);
            try (QuizDao quizDao = new QuizDao()) {
                Quizz quiz = quizDao.findById(id);

                if (quiz == null) {
                    req.setAttribute("error", "Quiz not found.");
                } else {
                    req.setAttribute("quiz", quiz);
                }

                req.getRequestDispatcher("/edit-quiz.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid quiz ID.");
            req.getRequestDispatcher("/edit-quiz.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");

            Quizz quiz = new Quizz(id, title, description);

            try (QuizDao quizDao = new QuizDao()) {
                int count = quizDao.update(quiz);
                System.out.println("Quiz updated: " + count);
            }

            resp.sendRedirect("admin.html");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

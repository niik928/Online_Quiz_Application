package com.quizz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizz.daos.QuizDao;
import com.quizz.entities.Quizz;

@WebServlet("/startquiz")
public class StartQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing quiz ID");
            return;
        }

        int quizId;
        try {
            quizId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quiz ID");
            return;
        }

        try (QuizDao quizDao = new QuizDao()) {
            Quizz quiz = quizDao.findById(quizId); 

            if (quiz == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz not found");
                return;
            }

            req.setAttribute("quiz", quiz); 
            req.getRequestDispatcher("/start-quiz.jsp").forward(req, resp); 

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load quiz");
        }
    }
}

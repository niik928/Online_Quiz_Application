
package com.quizz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.quizz.daos.QuizDao;
import com.quizz.entities.Quizz;

@WebServlet("/quizlist")
public class QuizListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try (QuizDao quizDao = new QuizDao()) {
            List<Quizz> list = quizDao.findAll();
            req.setAttribute("quizzes", list);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Unable to load quizzes.");
        }

        
        ServletContext ctx = getServletContext();
        String color = ctx.getInitParameter("color");
        req.setAttribute("color", color);

        
        req.getRequestDispatcher("/quizlist.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}


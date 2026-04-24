package com.quizz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizz.daos.QuizDao;
import com.quizz.entities.Quizz;

@WebServlet("/addquiz")
public class AddQuizServlet extends HttpServlet {
	
	private QuizDao quizDao;
	 
	@Override
	public void init()throws ServletException{
		try {
			quizDao = new QuizDao();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServletException("Failed to initialize QuizDao",e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		
		if (title == null || title.trim().isEmpty()) {
		    req.setAttribute("error", "Quiz title is required");
		    req.getRequestDispatcher("addquiz.jsp").forward(req, resp);
		    return;
		}

		try {
			Quizz quiz = new Quizz();
			quiz.setTitle(title);
			quiz.setDescription(description);
			 int quizId = quizDao.addQuiz(quiz);
			 if (quizId > 0) {
				 resp.sendRedirect("addquestions.jsp?quizId=" +quizId);
				 
				
			} else {
                 req.setAttribute("error","failed to add quiz");
                 req.getRequestDispatcher("addquiz.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("error", "error:" +e.getMessage());
			req.getRequestDispatcher("addquiz.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("quiz", new Quizz()); 
	    req.getRequestDispatcher("addquiz.jsp").forward(req, resp);
	}
}

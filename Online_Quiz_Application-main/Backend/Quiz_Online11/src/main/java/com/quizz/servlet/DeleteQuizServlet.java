package com.quizz.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizz.daos.QuizDao;
@WebServlet("/deleteQuiz")
public class DeleteQuizServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String quizId = req.getParameter("id");
		 int id = Integer.parseInt(quizId);
		 try(QuizDao quizDao = new QuizDao())
		 {
			 int count = quizDao.deleteById(id);
			 String message = "Quizz deleted:" +count;
			 System.out.println(message);
			 resp.sendRedirect("admin.html");
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException();
		}
		
	}
	

}

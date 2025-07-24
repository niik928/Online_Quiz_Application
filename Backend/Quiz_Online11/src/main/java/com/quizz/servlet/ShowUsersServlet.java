package com.quizz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.quizz.daos.UserDao;
import com.quizz.entities.User;

@WebServlet("/usershow")
public class ShowUsersServlet extends HttpServlet {

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "POST method not supported.");
}


@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(UserDao userDao = new UserDao()) {
			List<User> users = userDao.findAllUsers();
			req.setAttribute("users", users);
			RequestDispatcher rd = req.getRequestDispatcher("/usershow.jsp");
			rd.forward(req, resp);
		
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("Error retrieving users",e);
		}
	}
}

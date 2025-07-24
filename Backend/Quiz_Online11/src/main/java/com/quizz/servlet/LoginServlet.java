package com.quizz.servlet;

import com.quizz.daos.UserDao;
import com.quizz.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao;

    
    @Override
   
    public void init() throws ServletException {
        try {
            userDao = new UserDao(); 
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize UserDao", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        
        String email = req.getParameter("email");
        String password = req.getParameter("password");

      
        User user = userDao.login(email, password);

        if (user != null) {
          
            HttpSession session = req.getSession();
            session.setAttribute("role", user.getRole());
            session.setAttribute("user", user);

           
            if ("admin".equalsIgnoreCase(user.getRole())) {
                resp.sendRedirect("admin.html");
            } else {
                resp.sendRedirect("quizlist");
            }

        } else {
            
            req.setAttribute("error", "Invalid email or password.");
            req.getRequestDispatcher("index.jsp").forward(req, resp); 
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect("index.jsp");
    }
}

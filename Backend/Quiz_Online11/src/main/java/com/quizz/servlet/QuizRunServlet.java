

package com.quizz.servlet;

import com.quizz.daos.QuestionDao;
import com.quizz.entities.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/quizrun")
public class QuizRunServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    String idParam = req.getParameter("id");

	    if (idParam == null || idParam.trim().isEmpty()) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing quiz ID");
	        return;
	    }

	    int quizId;
	    try {
	        quizId = Integer.parseInt(idParam);
	    } catch (NumberFormatException e) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quiz ID format");
	        return;
	    }

	    try (QuestionDao dao = new QuestionDao()) {

	    	List<Question> questions = dao.findByQuizId(quizId);
	    	System.out.println("Question size: " + questions.size());  
	    	req.setAttribute("questions", questions);
	    	req.getRequestDispatcher("/quiz-run.jsp").forward(req, resp);

	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load quiz");
	    }
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp); 
    }
}

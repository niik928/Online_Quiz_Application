package com.quizz.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quizz.entities.Question;
import com.quizz.entities.Quizz;

public class QuestionDao implements AutoCloseable {
    private Connection con;

    public QuestionDao() throws Exception {
        con = DbUtil.getConnection();
    }

    public List<Question> findByQuizId(int quizId) throws SQLException {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE quiz_id = ?";
        System.out.println("Executing query: " + sql + " with quizId=" + quizId);
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuestionText(rs.getString("question_text"));
                q.setOptionA(rs.getString("option_a"));
                q.setOptionB(rs.getString("option_b"));
                q.setOptionC(rs.getString("option_c"));
                q.setOptionD(rs.getString("option_d"));
                q.setCorrectOption(rs.getString("correct_option"));
                list.add(q);
            }
        } catch (Exception e) {
            System.out.println("Error while fetching questions: " + e.getMessage());
            throw e;
        }
        System.out.println("Questions fetched: " + list.size());
        return list;
    }

    public List<Question> getAllQuestions() throws SQLException{
    	List<Question> list = new ArrayList<Question>();
    	String sql = "Select * from questions";
    	
    	try(PreparedStatement stmt = con.prepareStatement(sql)){
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next())
    		{
    			  Question q = new Question();
    			 q.setId(rs.getInt("id"));
                 q.setQuestionText(rs.getString("question_text"));
                 q.setOptionA(rs.getString("option_a"));
                 q.setOptionB(rs.getString("option_b"));
                 q.setOptionC(rs.getString("option_c"));
                 q.setOptionD(rs.getString("option_d"));
                 q.setCorrectOption(rs.getString("correct_option"));
                 list.add(q);
    			
    		}
    	}
		return list;
    	
    }
    
	
    public boolean addQuestion(Question question) throws Exception {
        String sql = "INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, question.getQuizId());
            stmt.setString(2, question.getQuestionText());
            stmt.setString(3, question.getOptionA());
            stmt.setString(4, question.getOptionB());
            stmt.setString(5, question.getOptionC());
            stmt.setString(6, question.getOptionD());
            stmt.setInt(7, Integer.parseInt(question.getCorrectOption())); // if correctOption is a String

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public void close() throws Exception {
        if (con != null) con.close();
    }
}

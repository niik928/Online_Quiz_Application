package com.quizz.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quizz.entities.Quizz;

public class QuizDao implements AutoCloseable {
	private Connection con;

	public QuizDao() throws Exception {
		con = DbUtil.getConnection();
	}
	
	public void close() throws Exception {
		if(con != null)
			con.close();
	}
	public List<Quizz> findAll() throws Exception {
		List<Quizz> list = new ArrayList<Quizz>();
		String sql = "SELECT * FROM quizzes";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					Quizz quiz = new Quizz(id, title, description);
					list.add(quiz);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}
		
	
	public Quizz findById(int id)throws Exception{
		String sql = "select * from quizzes where id= ?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					id = rs.getInt("id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					Quizz quiz = new Quizz(id,title,description);
					return quiz;
				}
				
			}
		
		}
		return null;
		
	}
	
	public int deleteById(int id) throws Exception{
		String sql = "Delete from Quizzes where id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, id);
            int count = stmt.executeUpdate();
            return count;
		}
	}
	
	public int update(Quizz quiz) throws Exception{
		String sql = "update quizzes set title=? ,description=? where id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1,quiz.getTitle());
			stmt.setString(2,quiz.getDescription());
			stmt.setInt(3,quiz.getId());
			 int count = stmt.executeUpdate();
			 return count;
		}

}
	
	public int addQuiz(Quizz quiz)throws Exception{
		int quizId= 0;
		String sql = "Insert into quizzes(title,description)values(?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			//stmt.setInt(1,quiz.getId());
			stmt.setString(1,quiz.getTitle());
			stmt.setString(2, quiz.getDescription());
			
			int rows = stmt.executeUpdate();

	        if (rows > 0) {
	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    quizId = rs.getInt(1); 
	                }
	            }
	        }
	    }

	    return quizId;
		
	}
}

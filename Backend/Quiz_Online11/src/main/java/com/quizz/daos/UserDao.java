package com.quizz.daos;

import com.quizz.entities.User;
import com.quizz.utils.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements AutoCloseable{
	private Connection con;
	
	public UserDao() throws Exception{
		con = DbUtil.getConnection();
	}
	@Override
	public void close() throws Exception {
		  if (con != null && !con.isClosed()) {
	            con.close();
	        }
		
	}

    public User login(String email, String password) {
   
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<User> findAllUsers() throws Exception{
    	List<User> users = new ArrayList<>();
    	String sql = "select * from Users";
    	try(PreparedStatement stmt = con.prepareStatement(sql);
    			ResultSet rs = stmt.executeQuery()) {
    		while(rs.next()){
    			User user = new User();
    			user.setId(rs.getInt("id"));
    			 user.setUsername(rs.getString("username"));
                 user.setEmail(rs.getString("email"));
                 user.setRole(rs.getString("role"));
                 users.add(user);
    		}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;
    	
    }

	
}

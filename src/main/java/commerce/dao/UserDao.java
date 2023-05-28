package commerce.dao;

import java.sql.*;
import commerce.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User userLogin(String email, String password) {
		User user = null;
        try {
            query = "select * from users where email=? and password=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            	user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
	public User getUserById(int id) {
		User user = null;
        try {
            query = "select * from users where id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            	user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
	
	
	public List<User> getAllUsers() {
	    List<User> userList = new ArrayList<>();
	    try {
	        query = "SELECT * FROM users";
	        pst = this.con.prepareStatement(query);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            // Ajouter d'autres attributs de l'utilisateur si nécessaire
	            userList.add(user);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	    return userList;
	}

	 public void cancelUser(int id) {
	        //boolean result = false;
	        try {
	            query = "delete from users where id=?";
	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            pst.execute();
	            //result = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.print(e.getMessage());
	        }
	        //return result;
	    }
	
    }

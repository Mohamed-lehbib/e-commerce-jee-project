package commerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RegisterServlet
 */

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 * Servlet implementation class RegisterServlet
 */
//@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String role = "no";
		
		if(!password.equals(confirmPassword)) {
			System.out.println("<p>Passwords do not match. Please try again.</p>");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart","root","");
				PreparedStatement ps = con.prepareStatement("insert into users (name, email, password, role) values (?, ?, ?, ?)");
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, password);
				ps.setString(4, role);
				ps.executeUpdate();
				con.close();
				System.out.println("<p>User registered successfully.</p>");
				response.sendRedirect("login.jsp");
			} catch (Exception e) {
				System.out.println("<p>Error: " + e.getMessage() + "</p>");
			}
		}
	}
	}

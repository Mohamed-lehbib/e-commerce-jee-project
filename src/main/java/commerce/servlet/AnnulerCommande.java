package commerce.servlet;


/**
 * Servlet implementation class AnnulerCommandeServlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import commerce.connection.DbCon;
import commerce.dao.CommandeDao;

//@WebServlet("/cancel-Commander")
public class AnnulerCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if(id != null) {
				CommandeDao commandeDao = new CommandeDao(DbCon.getConnection());
				commandeDao.cancelCommande(Integer.parseInt(id));
			}
			response.sendRedirect("commande3.jsp");
		} catch (ClassNotFoundException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}

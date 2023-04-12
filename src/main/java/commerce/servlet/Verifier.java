package commerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import commerce.connection.DbCon;
import commerce.dao.CommandeDao;
import commerce.model.*;


//@WebServlet("/Panier-check-out")
public class Verifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
			ArrayList<Panier> panier_list = (ArrayList<Panier>) request.getSession().getAttribute("panier-list");
			User auth = (User) request.getSession().getAttribute("auth");
			if(panier_list != null && auth!=null) {
				for(Panier c:panier_list) {
					Commande commande = new Commande();
					commande.setId(c.getId());
					commande.setUid(auth.getId());
					commande.setQunatity(c.getQuantity());
					commande.setDate(formatter.format(date));
					
					CommandeDao oDao = new CommandeDao(DbCon.getConnection());
					boolean result = oDao.insertCommande(commande);
					if(!result) break;
				}
				panier_list.clear();
				response.sendRedirect("commande.jsp");
			}else {
				if(auth==null) {
					response.sendRedirect("login.jsp");
				}
				response.sendRedirect("panier.jsp");
			}
		} catch (ClassNotFoundException|SQLException e) {
			
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

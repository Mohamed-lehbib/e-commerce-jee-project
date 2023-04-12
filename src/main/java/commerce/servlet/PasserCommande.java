package commerce.servlet;


/**
 * Servlet implementation class PasserCommandeServlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import commerce.connection.DbCon;
import commerce.dao.*;
import commerce.model.*;


//@WebServlet("/passer-commande-servlet")
public class PasserCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User auth = (User) request.getSession().getAttribute("auth");

            if (auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                	productQuantity = 1;
                }
                Commande commandeModel = new Commande();
                commandeModel.setId(Integer.parseInt(productId));
                commandeModel.setUid(auth.getId());
                commandeModel.setQunatity(productQuantity);
                commandeModel.setDate(formatter.format(date));

                CommandeDao commandeDao = new CommandeDao(DbCon.getConnection());
                boolean result = commandeDao.insertCommande(commandeModel);
                if (result) {
                    ArrayList<Panier> panier_list = (ArrayList<Panier>) request.getSession().getAttribute("panier-list");
                    if (panier_list != null) {
                        for (Panier c : panier_list) {
                            if (c.getId() == Integer.parseInt(productId)) {
                                panier_list.remove(panier_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    response.sendRedirect("commande.jsp");
                } else {
                    out.println("Commande failed");
                }
            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (ClassNotFoundException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

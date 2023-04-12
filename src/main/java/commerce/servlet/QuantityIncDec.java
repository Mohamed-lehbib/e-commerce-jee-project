package commerce.servlet;


/**
 * Servlet implementation class QuantityIncDecServlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import commerce.model.Panier;

//@WebServlet("/quantity-inc-dec")
public class QuantityIncDec extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Panier> panier_list = (ArrayList<Panier>) request.getSession().getAttribute("panier-list");

			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (Panier c : panier_list) {
						if (c.getId() == id) {
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("panier.jsp");
						}
					}
				}

				if (action.equals("dec")) {
					for (Panier c : panier_list) {
						if (c.getId() == id && c.getQuantity() > 1) {
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
							break;
						}
					}
					response.sendRedirect("panier.jsp");
				}
			} else {
				response.sendRedirect("panier.jsp");
			}
		}
	}

}

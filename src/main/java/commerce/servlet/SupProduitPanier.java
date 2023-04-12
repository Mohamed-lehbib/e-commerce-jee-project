package commerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SupProduitPanierServlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;




import commerce.model.Panier;

//@WebServlet("/remove-from-Panier")
public class SupProduitPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String bookId = request.getParameter("id");
			if (bookId != null) {
				ArrayList<Panier> panier_list = (ArrayList<Panier>) request.getSession().getAttribute("panier-list");
				if (panier_list != null) {
					for (Panier c : panier_list) {
						if (c.getId() == Integer.parseInt(bookId)) {
							panier_list.remove(panier_list.indexOf(c));
							break;
						}
					}
				}
				response.sendRedirect("panier.jsp");

			} else {
				response.sendRedirect("panier.jsp");
			}

		}
	}

}


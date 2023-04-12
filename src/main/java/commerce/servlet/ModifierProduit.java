package commerce.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import commerce.connection.DbCon;
import commerce.dao.ProduitDao;
import commerce.model.Produit;

//@WebServlet("/ModifierProduit")
public class ModifierProduit extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            ProduitDao pd = new ProduitDao(DbCon.getConnection());
            Produit produit = pd.getProduitById(id);
            if (produit == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            request.setAttribute("produit", produit);
            request.getRequestDispatcher("modifier.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("index.jsp");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            String name = request.getParameter("name");
            String categorie = request.getParameter("categorie");
            double price = Double.parseDouble(request.getParameter("price"));
            ProduitDao pd = new ProduitDao(DbCon.getConnection());
            Produit produit = pd.getProduitById(id);
            if (produit == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            produit.setName(name);
            produit.setCategory(categorie);
            produit.setPrice(price);
            pd.updateProduit(produit);
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException e) {
            response.sendRedirect("index.jsp");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

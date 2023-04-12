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

@WebServlet("/SupProduit")
public class SupProduit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the ID of the product to delete from the request parameter
        int productId = Integer.parseInt(request.getParameter("id"));

        // Delete the product from the database
        ProduitDao pd;
		try {
			pd = new ProduitDao(DbCon.getConnection());
			 pd.deleteProduit(productId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       

        // Redirect back to the index page
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

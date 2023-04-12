package commerce.dao;

import java.sql.*;
import java.util.*;

import commerce.connection.DbCon;
import commerce.model.Panier;
import commerce.model.Produit;

public class ProduitDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    

	public ProduitDao(Connection con) {
		super();
		this.con = con;
	}
	
	
	public List<Produit> getAllProduits() {
        List<Produit> book = new ArrayList<>();
        try {

            query = "select * from Products";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	Produit row = new Produit();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
	
	
	 public Produit getSingleProduit(int id) {
		 Produit row = null;
	        try {
	            query = "select * from Products where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Produit();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString("category"));
	                row.setPrice(rs.getDouble("price"));
	                row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	
	public double getTotalPanierPrice(ArrayList<Panier> PanierList) {
        double sum = 0;
        try {
            if (PanierList.size() > 0) {
                for (Panier item : PanierList) {
                    query = "select price from Products where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

    
    public List<Panier> getPanierProduits(ArrayList<Panier> PanierList) {
        List<Panier> book = new ArrayList<>();
        try {
            if (PanierList.size() > 0) {
                for (Panier item : PanierList) {
                    query = "select * from Products where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Panier row = new Panier();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
    
    public List<String> getAllCategories() throws SQLException {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM products";
        
             pst = this.con.prepareStatement(sql);
             rs = pst.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                categories.add(category);
            }
        
        return categories;
    }
    
    public List<Produit> getProduitsByCategory(String category) {
        List<Produit> produits = new ArrayList<>();
        try {
            pst = this.con.prepareStatement("SELECT * FROM products WHERE category = ?");
            pst.setString(1, category);
            rs = pst.executeQuery();
            while (rs.next()) {
                Produit produit = new Produit(rs.getInt("id"), rs.getString("name"),rs.getString("category"), rs.getDouble("price"),  rs.getString("image"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }
    
    public void deleteProduit(int produitId) {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = this.con.prepareStatement(query)) {
            stmt.setInt(1, produitId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // handle exception
        }
    }
    
    public Produit getProduitById(int id) throws ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Produit p = null;

        try {
            ps = this.con.prepareStatement("SELECT * FROM products WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Produit();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getDouble("price"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return p;
    }
    
    public boolean updateProduit(Produit produit) throws ClassNotFoundException, SQLException {
        boolean success = false;
            String query = "UPDATE products SET name=?, price=?, category=? WHERE id=?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, produit.getName());
            pstmt.setDouble(2, produit.getPrice());
            pstmt.setString(3, produit.getCategory());
            pstmt.setInt(4, produit.getId());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        return success;
    }



}

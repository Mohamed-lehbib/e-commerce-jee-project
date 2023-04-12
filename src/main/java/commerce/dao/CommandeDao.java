package commerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import commerce.model.*;

public class CommandeDao {
	
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    

	public CommandeDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertCommande(Commande model) {
        boolean result = false;
        try {
            query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId());
            pst.setInt(2, model.getUid());
            pst.setInt(3, model.getQunatity());
            pst.setString(4, model.getDate());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	

    public List<Commande> userCommandes(int id) {
        List<Commande> list = new ArrayList<>();
        try {
            query = "select * from orders where u_id=? order by orders.o_id desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Commande Commande = new Commande();
                ProduitDao produitDao = new ProduitDao(this.con);
                int pId = rs.getInt("p_id");
                Produit produit = produitDao.getSingleProduit(pId);
                Commande.setCommandeId(rs.getInt("o_id"));
                Commande.setId(pId);
                Commande.setName(produit.getName());
                Commande.setCategory(produit.getCategory());
                Commande.setPrice(produit.getPrice()*rs.getInt("o_quantity"));
                Commande.setQunatity(rs.getInt("o_quantity"));
                Commande.setDate(rs.getString("o_date"));
                list.add(Commande);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelCommande(int id) {
        //boolean result = false;
        try {
            query = "delete from orders where o_id=?";
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

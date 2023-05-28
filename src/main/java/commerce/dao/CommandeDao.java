package commerce.dao;
import commerce.model.Produit;
import commerce.model.User;
import commerce.model.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



import java.util.ArrayList;
import java.util.List;





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
    public List<Commande> getAllCommandes() {
        List<Commande> list = new ArrayList<>();
        try {
            query = "SELECT * FROM orders ORDER BY orders.o_id DESC";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                Commande commande = new Commande();
                ProduitDao produitDao = new ProduitDao(this.con);
                UserDao userDao = new UserDao(this.con);
                
                int pId = rs.getInt("p_id");
                int uId = rs.getInt("u_id");
                Produit produit = produitDao.getSingleProduit(pId);
                User user = userDao.getUserById(uId);
                
                commande.setCommandeId(rs.getInt("o_id"));
                commande.setUid(rs.getInt("u_id"));
                commande.setUemail(user.getEmail());
                commande.setName(produit.getName());
                commande.setCategory(produit.getCategory());
                commande.setQunatity(rs.getInt("o_quantity"));
                commande.setPrice(produit.getPrice() * rs.getInt("o_quantity"));
                commande.setDate(rs.getString("o_date"));
                
                list.add(commande);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }




    
}

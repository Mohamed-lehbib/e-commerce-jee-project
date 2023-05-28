package commerce.model;
import commerce.model.User;
import commerce.model.Produit;
public class Commande extends Produit{
	private int commandeId;
	private int uid;
	private int qunatity;
	private String date;
	private String uemail;
	private double price;
	private String category;
	
	
	    
	

	public Commande() {
	}
	
	public Commande(int commandeId, int uid, int qunatity, String date, String uemail,float price ,String category) {
		super();
		this.commandeId = commandeId;
		this.uid = uid;
		this.qunatity = qunatity;
		this.date = date;
		this.uemail = uemail;
		this.price = price;
		this.category = category;
	}

	public Commande(int uid, int qunatity, String date, String uemail,String category) {
		super();
		this.uid = uid;
		this.qunatity = qunatity;
		this.date = date;
		this.uemail = uemail;
		this.category = category;
	}

	public int getCommandeId() {
		return commandeId;
	}
	public void setCommandeId(int commandeId) {
		this.commandeId = commandeId;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQunatity() {
		return qunatity;
	}
	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}


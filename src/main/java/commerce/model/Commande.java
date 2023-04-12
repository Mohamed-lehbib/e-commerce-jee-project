package commerce.model;

public class Commande extends Produit{
	private int commandeId;
	private int uid;
	private int qunatity;
	private String date;
	
	public Commande() {
	}
	
	public Commande(int commandeId, int uid, int qunatity, String date) {
		super();
		this.commandeId = commandeId;
		this.uid = uid;
		this.qunatity = qunatity;
		this.date = date;
	}

	public Commande(int uid, int qunatity, String date) {
		super();
		this.uid = uid;
		this.qunatity = qunatity;
		this.date = date;
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
}


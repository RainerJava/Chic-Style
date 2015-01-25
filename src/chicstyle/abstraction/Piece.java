package chicstyle.abstraction;


public class Piece {
	
	private String id_piece;
	private String client;
	private String modele;
	private String tissu;
	private String couleur;
	private String type;

	
	public Piece(String client,
			 String modele,
			 String tissu,
			 String couleur,
			 String type) {
	super();
	this.client = client;
	this.modele = modele;
	this.tissu = tissu;
	this.couleur = couleur;
	this.type = type;
	this.id_piece = client + modele + tissu + couleur;
}

public Piece() {
	super();
	this.client = "";
	this.modele = "";
	this.tissu = "";
	this.couleur = "";
	this.type = "";
	this.id_piece = "";
}


	public String getId_piece() {
		return id_piece;
	}


	public void setId_piece(String id_piece) {
		this.id_piece = id_piece;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getModele() {
		return modele;
	}


	public void setModele(String modele) {
		this.modele = modele;
	}


	public String getTissu() {
		return tissu;
	}


	public void setTissu(String tissu) {
		this.tissu = tissu;
	}


	public String getCouleur() {
		return couleur;
	}


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return this.getId_piece() + ", "+ this.getClient()+ ", "+ this.getModele()+ ", "+ this.getTissu()+ ", "+ this.getCouleur()+ ", "+ this.getType();
	}
	
	
}

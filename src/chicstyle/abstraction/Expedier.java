package chicstyle.abstraction;



public class Expedier {
	
	private String id_piece;
	private int taille;
	private int qte;

	public Expedier(String id_piece, int taille, int qte) {
		super();
		this.id_piece = id_piece;
		this.taille = taille;
		this.qte = qte;
	}
	
	public String getId_piece() {
		return id_piece;
	}

	public void setId_piece(String id_piece) {
		this.id_piece = id_piece;
	}



	public int getTaille() {
		return taille;
	}



	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public int getQte() {
		return this.qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}
	public String toString(){
		return "" + this.getQte();
	}
	
}

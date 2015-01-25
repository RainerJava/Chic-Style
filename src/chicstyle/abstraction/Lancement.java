package chicstyle.abstraction;

public class Lancement {
	
	private int qte;
	private String id_piece;
	private int taille;
	
	
	public Lancement(String id_piece, int taille, int qte) {
		super();
		this.id_piece = id_piece;
		this.taille = taille;
		this.qte = qte;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
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
}

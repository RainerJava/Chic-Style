package chicstyle.abstraction;

public class Patron {
	
	private String id_piece;
	private String ref;
	private int taille;
	private double laize;
	private double longueur;
	
	
	public Patron(String id_piece, String ref, int taille,double laize, double longueur) {
		super();
		this.id_piece = id_piece;
		this.ref=ref;
		this.taille=taille;
		this.laize = laize;
		this.longueur = longueur;
	}
	
	public Patron() {
		super();
		this.id_piece = "";
		this.ref="";
		this.taille=0;
		this.laize = 0;
		this.longueur = 0;
	}
	


	public String getId_piece() {
		return id_piece;
	}


	public void setId_piece(String id_piece) {
		this.id_piece = id_piece;
	}


	public String getRef() {
		return ref;
	}


	public void setRef(String ref) {
		this.ref = ref;
	}


	public int getTaille() {
		return taille;
	}


	public void setTaille(int taille) {
		this.taille = taille;
	}


	public double getLaize() {
		return laize;
	}


	public void setLaize(double laize) {
		this.laize = laize;
	}

	public double getLongueur() {
		
		return longueur;
		
	}


	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}
	
		

}

package chicstyle.tableaux;

public class TabDirectionProduitsFinis1 {

	private String modele;
	private String tissu;
	private String couleur;
	private int taille;
    private int housse;
    private int expedie;
    private int total;
    private int diff;
 
    public TabDirectionProduitsFinis1(String modele, String tissu, String couleur, int taille, int housse, int expedie, int total, int diff) {
        super();
 
        this.modele = modele;
        this.tissu = tissu;
        this.couleur = couleur;
        this.taille = taille;
        this.housse = housse;
        this.expedie = expedie;
        this.total = total;
        this.diff = diff;
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

	public int getTaille() {
        return taille;
    }
 
    public void setTaille(int taille) {
        this.taille = taille;
    }
 
    public int getHousse() {
        return housse;
    }
 
    public void setHousse(int housse) {
        this.housse = housse;
    }
 
    public int getExpedie() {
        return expedie;
    }
 
    public void setExpedie(int expedie) {
        this.expedie = expedie;
    }
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }
    
    public int getDiff() {
    	return diff;
    }
    
    public void setDiff(int diff) {
    	this.diff = diff;
    }
}

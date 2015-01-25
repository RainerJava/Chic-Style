package chicstyle.tableaux;

public class TabPret1 {
	
	private String client;
    private String modele;
    private String tissu;
    private String couleur;
    private String taille;
    private String quantite;
 
    public TabPret1(String client, String modele,String tissu, String couleur, String taille,String quantite) {
        super();
 
        this.client = client;
        this.modele = modele;
        this.tissu = tissu;
        this.couleur = couleur;
        this.taille = taille;
        this.quantite = quantite;
    }
 
    public String getClient() {
        return client;
    }
 
    public void setClient(String client) {
        this.client=client;
    }
 
    public String getModele() {
        return modele;
    }
 
    public void setModele(String modele) {
        this.modele=modele;
    }
 
    public String getTissu() {
        return tissu;
    }
 
    public void setTissu(String tissu) {
        this.tissu=tissu;
    }
 
    public String getCouleur() {
        return couleur;
    }
 
    public void setCouleur(String couleur) {
        this.couleur=couleur;
    }
    
    public String getTaille() {
    	return taille;
    }
    
    public void setTaille(String taille) {
    	this.taille=taille;
    }
    
    public String getQuantite() {
    	return quantite;
    }
    
    public void setQuantite(String quantite){
    	this.quantite=quantite;
    }

}

package chicstyle.tableaux;

public class TabExpedition1 {

	private int taille;
	private int pret;
    private int quantite;
    private int reste;
    private int total;
 
    public TabExpedition1(int taille,int pret, int quantite, int reste, int total) {
        super();
 
        this.taille = taille;
        this.pret = pret;
        this.quantite = quantite;
        this.reste = reste;
        this.total = total;
    }
 
    public int getTaille() {
        return taille;
    }
 
    public void setTaille(int taille) {
        this.taille = taille;
    }
    
    public int getPret() {
    	return pret;
    }
    
    public void setPret(int pret) {
    	this.pret = pret;
    }
 
    public int getQuantite() {
        return quantite;
    }
 
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
 
    public int getReste() {
        return reste;
    }
 
    public void setReste(int reste) {
        this.reste = reste;
    }
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }
}

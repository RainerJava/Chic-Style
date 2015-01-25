package chicstyle.tableaux;

public class TabLancement1 {

	private String taille;
    private int qte;
 
    public TabLancement1(String taille, int qte) {
        super();

        this.taille = taille;
        this.qte = qte;
    }

	public String getTaille() {
        return taille;
    }
 
    public void setTaille(String taille) {
        this.taille = taille;
    }
 
    public int getQte() {
        return qte;
    }
 
    public void setQte(int qte) {
        this.qte = qte;
    }
}

package chicstyle.tableaux;

public class TabVanessaHoussage1 {

	private int taille;
    private int housse;
    private int total;
    private int reste;

 
    public TabVanessaHoussage1(int taille, int housse, int total, int reste) {
        super();
 
        this.taille = taille;
        this.housse = housse;
        this.total = total;
        this.reste = reste;
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
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }
 
    public int getReste() {
        return reste;
    }
 
    public void setReste(int reste) {
        this.reste = reste;
    }
}

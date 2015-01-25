package chicstyle.tableaux;

public class TabNomenclature1 {

	private String reference;
    private String type;
    private double qteUnitaire;
    private boolean facture;
    private boolean tissu;
 
    public TabNomenclature1(String reference, String type,double qteUnitaire, boolean facture, boolean tissu) {
        super();
 
        this.reference = reference;
        this.type = type;
        this.qteUnitaire = qteUnitaire;
        this.facture = facture;
        this.tissu = tissu;
    }
 
    public String getReference() {
        return reference;
    }
 
    public void setReference(String reference) {
        this.reference = reference;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public double getqteUnitaire() {
        return qteUnitaire;
    }
 
    public void setqteUnitaire(double qteUnitaire) {
        this.qteUnitaire = qteUnitaire;
    }
 
    public boolean isFacture() {
        return facture;
    }
 
    public void setFacture(boolean facture) {
        this.facture = facture;
    }
    
    public boolean isTissu() {
    	return tissu;
    }
    
    public void setTissu(boolean tissu) {
    	this.tissu = tissu;
    }
 
}

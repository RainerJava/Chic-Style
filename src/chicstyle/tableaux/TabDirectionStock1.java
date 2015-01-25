package chicstyle.tableaux;

public class TabDirectionStock1 {

	private String reference;
    private String type;
    private int besoin;
    private int dispo;
    private int diff;
 
    public TabDirectionStock1(String reference, String type, int besoin, int dispo, int diff ) {
        super();
 
        this.reference = reference;
        this.type = type;
        this.besoin = besoin;
        this.dispo = dispo;
        this.diff = diff;
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
 
    public int getBesoin() {
        return besoin;
    }
 
    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }
 
    public int getDispo() {
        return dispo;
    }
 
    public void setDispo(int dispo) {
        this.dispo = dispo;
    }
    
    public int getDiff() {
    	return diff;
    }
    
    public void setDiff(int diff) {
    	this.diff = diff;
    }
 
}

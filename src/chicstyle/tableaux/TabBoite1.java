package chicstyle.tableaux;

public class TabBoite1 {
	

	private String reference;
    private String type;
    private int qte;
    private int stock;
    private boolean fait;
 
    public TabBoite1(String reference, String type,int qte, int stock, boolean fait) {
        super();
 
        this.reference = reference;
        this.type = type;
        this.qte = qte;
        this.stock = stock;
        this.fait = fait;
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
 
    public int getqte() {
        return qte;
    }
 
    public void setqte(int qte) {
        this.qte = qte;
    }

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isFait() {
		return fait;
	}

	public void setFait(boolean fait) {
		this.fait = fait;
	}
}

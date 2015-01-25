package chicstyle.tableaux;

public class TabStockVanessa1 {
	
	private String reference;
    private String type;
    private int stock;
 
    public TabStockVanessa1(String type, String reference, int stock) {
        super();
 
        this.reference = reference;
        this.type = type;
        this.stock = stock;
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
 

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
    


}

package chicstyle.abstraction;



public class Referencage {
	
	private String ref;
	private String type;
	private boolean estTissu;
	
	
	public Referencage(String ref, String type, boolean estTissu) {
		super();
		this.ref = ref;
		this.type = type;
		this.estTissu = estTissu;
	}


	public String getRef() {
		return ref;
	}


	public void setRef(String ref) {
		this.ref = ref;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public boolean isEstTissu() {
		return estTissu;
	}


	public void setEstTissu(boolean estTissu) {
		this.estTissu = estTissu;
	}


	
}

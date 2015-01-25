package chicstyle.abstraction;


public class Recep_Tissu {

	private String ref;
	private double laize;
	private double longueur_rouleau;
	
	public Recep_Tissu(String ref, double laize, double longueur_rouleau) {
		super();
		this.ref = ref;
		this.laize = laize;
		this.longueur_rouleau = longueur_rouleau;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public double getLaize() {
		return this.laize;
	}
	public void setLaize(double laize) {
		this.laize = laize;
	}
	public double getLongueur_rouleau() {
		return this.longueur_rouleau;
	}
	public void setLongueur_rouleau(double longueur_rouleau) {
		this.longueur_rouleau = longueur_rouleau;
	}
	public String toString() {
		return this.getLaize() + ", " + this.getLongueur_rouleau();
	}

}

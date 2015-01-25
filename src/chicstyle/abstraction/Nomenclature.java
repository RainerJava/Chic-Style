package chicstyle.abstraction;


public class Nomenclature {

	private double qte_unit;
	private String ref;
	private boolean fact;
	public String id_piece;

	public Nomenclature(String id_piece, String ref, double qte_unit,  boolean fact) {
		super();
		this.qte_unit = qte_unit;
		this.ref = ref;
		this.fact = fact;
		this.id_piece = id_piece;
	}

	public double getQte_unit() {
		return qte_unit;
	}

	public void setQte_unit(double qte_unit) {
		this.qte_unit = qte_unit;
	}

	public boolean isFact() {
		return fact;
	}

	public void setFact(boolean fact) {
		this.fact = fact;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getId_piece() {
		return id_piece;
	}

	public void setId_piece(String id_piece) {
		this.id_piece = id_piece;
	}

	@Override
	public String toString() {

		if (this.isFact()){
			return this.getQte_unit()+", facturé";
		}
		else {
			return this.getQte_unit()+", non facturé";
		}
	}


}

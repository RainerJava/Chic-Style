package chicstyle.abstraction;

public class LongueurCoupee {

	private String id_piece;
	private String ref;
	private int longueurCoupee;

	public LongueurCoupee(String id_piece, String ref, int longueurCoupee) {
		super();
		this.id_piece = id_piece;
		this.ref = ref;
		this.longueurCoupee = longueurCoupee;
	}

	public String getId_piece() {
		return id_piece;
	}

	public void setId_piece(String id_piece) {
		this.id_piece = id_piece;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public int getLongueurCoupee() {
		return longueurCoupee;
	}

	public void setLongueurCoupee(int longueurCoupee) {
		this.longueurCoupee = longueurCoupee;
	}
}


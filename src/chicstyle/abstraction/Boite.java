package chicstyle.abstraction;

public class Boite {

	private String id_piece;
	private String ref;
	private boolean ok;

	public Boite(String id_piece, String ref, boolean ok) {
		super();
		this.id_piece = id_piece;
		this.ref = ref;
		this.ok = ok;
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

	public boolean isOk() {
		return this.ok;
	}

	public void setDefinitive(boolean ok) {
		this.ok = ok;
	}
	public String toString() {
		return "" + this.isOk();
	}

}
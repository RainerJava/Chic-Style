package chicstyle.abstraction;

public class Recep_Fournitures {

	private String ref;
	private int qte_Recue;

	public Recep_Fournitures(String ref, int qte_Recue) {
		super();
		this.ref = ref;
		this.qte_Recue = qte_Recue;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public int getQte_Recue() {
		return this.qte_Recue;
	}

	public void setQte_Recue(int qte_Recue) {
		this.qte_Recue = qte_Recue;
	}

	public String toString() {
		return "" + this.getQte_Recue();
	}
}

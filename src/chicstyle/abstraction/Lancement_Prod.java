package chicstyle.abstraction;

import java.sql.Date;


public class Lancement_Prod {
	
	private String id_piece;
	private String date;

	public Lancement_Prod(String id_piece, String date) {
		super();
		this.id_piece = id_piece;
		this.date = date;
	}

	public String getId_piece() {
		return id_piece;
	}

	public void setId_piece(String id_piece) {
		this.id_piece = id_piece;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}

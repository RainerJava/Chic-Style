package chicstyle.abstraction;

public class Alertes {
	
	private String source;
	private String message;
	
	
	public Alertes(String source, String message) {
		super();
		this.source = source;
		this.message = message;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

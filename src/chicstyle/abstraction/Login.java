package chicstyle.abstraction;


public class Login {

	private String login;
	private String mdp;
	private boolean direction;
	private boolean catherine;
	private boolean vanessa;
	private boolean claudie;
	private boolean marlene;

	public Login(String login, String mdp, boolean direction,
			boolean catherine, boolean vanessa, boolean claudie, boolean marlene) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.direction = direction;
		this.catherine = catherine;
		this.vanessa = vanessa;
		this.claudie = claudie;
		this.marlene = marlene;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public boolean isDirection() {
		return this.direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public boolean isCatherine() {
		return this.catherine;
	}

	public void setCatherine(boolean catherine) {
		this.catherine = catherine;
	}

	public boolean isVanessa() {
		return this.vanessa;
	}

	public void setVanessa(boolean vanessa) {
		this.vanessa = vanessa;
	}

	public boolean isClaudie() {
		return this.claudie;
	}

	public void setClaudie(boolean claudie) {
		this.claudie = claudie;
	}

	public boolean isMarlene() {
		return this.marlene;
	}

	public void setMarlene(boolean marlene) {
		this.marlene = marlene;
	}

	public String toString() {
		return this.getLogin() + ", " + this.getMdp() + ", " + this.isDirection() + ", "
				+this.isCatherine()+ ", "+this.isVanessa()+ ", "+this.isClaudie()+ ", "
				+this.isMarlene();
	}

}

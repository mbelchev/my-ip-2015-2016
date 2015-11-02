package ip.availability;

public class User {
	
	private String name;
	private Boolean logged;
	
	public User(String name) {
		this.name = name;
		this.logged = true;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getLogged() {
		return this.logged;
	}
	
	public void setLogged(Boolean logged) {
		this.logged = logged;
	}
	
}

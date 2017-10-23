package customDiff.SPLdomain;

public class Developer {
	private int id;
	private String name;
	private String email;
	
	public Developer(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}

package model.pojos;

public class Role {

	private int id_role;
	private String type_role;

	// Default constructor
	public Role() {
		super();
		this.id_role = 1;
		this.type_role = "";
	}

	public Role(int id_role) {
		this();
		this.id_role = id_role;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getType_role() {
		return type_role;
	}

	public void setType_role(String type_role) {
		this.type_role = type_role;
	}

	@Override
	public String toString() {
		return "Role [id_role=" + id_role + ", type_role=" + type_role + "]";
	}
}

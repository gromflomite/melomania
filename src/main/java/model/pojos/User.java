package model.pojos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

	private int id;
	
	@NotBlank(message = "Invalid name")
	@Size(min = 4, max = 12, message = "Nick must be between 4 and 12 characters")
	private String name;
	
	@Email(message = "Invalid email")
	private String email;
	
	private Role role;
	
	@NotBlank(message = "Invalid password")
	// Setting max 70 to allow SHA256 hash long enough
	@Size(min = 4, max = 70, message = "Password must be between 4 and 12 characters")
	private String password;

	// Default constructor
	public User() {
		super();
		this.id = 0;
		this.name = "";
		this.email = "";
		this.role = new Role();
		this.password = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + ", password=" + password + "]";
	}
}

package jp.co.axa.apidemo.entities;

import java.util.List;

public class UserInfoResponse {
	
	Long Id;
	public UserInfoResponse(Long id, String userName, String email, List<String> roles) {
		super();
		Id = id;
		this.userName = userName;
		this.email = email;
		this.role = roles;
	}
	String userName;
	String email;
	List<String> role;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	

}

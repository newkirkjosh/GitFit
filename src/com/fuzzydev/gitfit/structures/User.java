package com.fuzzydev.gitfit.structures;

public class User {

	private String ID;
	private String userType;
	private String firstName;
	private String lastName;
	private String weight;
	private String height;
	private String email;
	private Workout mWorkouts;
	
	
	User(){//No-Arg-Constructor
		
	}
	public User(String firstName){
		this.firstName = firstName;
	}
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getWeight() {
		return weight;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getHeight() {
		return height;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Workout getmWorkouts() {
		return mWorkouts;
	}
	
	public void setmWorkouts(Workout mWorkouts) {
		this.mWorkouts = mWorkouts;
	}
}

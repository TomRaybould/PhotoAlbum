package application;

import java.util.ArrayList;

public class Users {
	String userName;
	String password;
	boolean admin;
	/// should probably make 2 subclasses
	static ArrayList<Users> allUsers = new ArrayList<Users>();
	
	public Users(String userName, String password){
		this.userName = userName;
		this.password = password;
		admin = false;
	}
	
	static void addUser(Users u){
		allUsers.add(u);
	}
	
	static void deleteUser(Users u){
		
		for(Users a : allUsers){
			if(a.equals(u)){
				allUsers.remove(a);
			}
		}
	}
	
	static boolean checkIfInSystem(Users u){
		for(Users a : allUsers){
			if(a.equals(u)){
				return true;
			}
		}
		return false;
	}
	
	void setAdmin(){
		this.admin = true;
	}
	
	public boolean equals(Object o){
		if (!(o instanceof Users)){
			return false;
		}
		Users u=(Users)o;
		
		String userName = this.userName;
		
		if(!userName.equals(u.userName)){
			return false;
		}
		return true;
	}
}

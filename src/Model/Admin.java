package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin implements Serializable{
	private String name;
	private String password;
	private ArrayList<User> listOfUsers = User.allUsers;
	//this class should be able to add and delete users
	// not much else to this class
	public Admin(String name, String password){
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<User> getListOfUsers() {
		return listOfUsers;
	}
	
	private void createUser(User u){
		listOfUsers.add(u);
	}
	
	private void deleteUser(User u){
		for(User a : this.listOfUsers){
			if(a.equals(u)){
				this.listOfUsers.remove(a);
			}
		}
	}
	
	

}
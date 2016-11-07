package Model;

import java.util.ArrayList;

public class Admin {
	String name;
	String password;
	ArrayList<User> listOfUsers = new ArrayList<User>();
	//this class should be able to add and delete users
	// not much else to this class
	public Admin(String name, String password, ArrayList<User> listOfUsers){
		this.name = name;
		this.password = password;
		this.listOfUsers = listOfUsers;
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

	public void setListOfUsers(ArrayList<User> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}
	
	private void create(User u){
		listOfUsers.add(u);
	}
	
	private void delete(User u){
		for(User a : this.listOfUsers){
			if(a.equals(u)){
				this.listOfUsers.remove(a);
			}
		}
	}

}

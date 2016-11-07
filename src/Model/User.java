package Model;

import java.util.ArrayList;
import java.io.*;

public class User implements Serializable{
	public static final String storeDir = "dat";
	public static final String storeFile = "Users.dat";
	static ArrayList<User> allUsers = new ArrayList<User>();
	
	private String userName;
	private String password;
	private ArrayList<Album> albumList = new ArrayList<Album>();
	private ArrayList<String> tagTypes = new ArrayList<String>();
	
	// This class will be a user
	// The user should be made of a name/password combo
	// a list of all of its albums
	// and a list of all of its tags
	// album will be it own class that will consist of a list of photos
	// Tag will also be its own class
	// basic serializtion code was also put into this class as a basic sample outline from class
	// user should also be able to search, but that may be its own class
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	
	public static void write(User u) throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream (
				new FileOutputStream(storeDir +File.separator +storeFile));
		oos.writeObject(u);
	}
	public static User read()
	           throws IOException, ClassNotFoundException {
	           ObjectInputStream ois = new ObjectInputStream(
	                new FileInputStream(storeDir + File.separator + storeFile));
	           User u = (User)ois.readObject();
	           return u;
	        }
	public static void addUser(User u){
		allUsers.add(u);
	}
	
	public static void deleteUser(User u){
		
		for(User a : allUsers){
			if(a.equals(u)){
				allUsers.remove(a);
			}
		}
	}
	
	public static boolean checkIfInSystem(User u){
		for(User a : allUsers){
			if(a.equals(u)){
				return true;
			}
		}
		return false;
	}
	
	
	public boolean equals(Object o){
		if (!(o instanceof User)){
			return false;
		}
		User u=(User)o;
		
		String userName = this.userName;
		
		if(!userName.equals(u.userName)){
			return false;
		}
		return true;
	}
}

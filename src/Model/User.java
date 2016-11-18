package Model;

import java.util.ArrayList;
import java.io.*;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4640600435000251009L;
	private static final String storeDir = "dat";
	private static final String storeFile = "Users.dat";
	private static ArrayList<User> allUsers = new ArrayList<User>();
	private static User currentUser;
	private String userName;
	private String password;
	private ArrayList<Album> albumList = new ArrayList<Album>();
	private ArrayList<String> tagTypes = new ArrayList<String>();
	static public ObjectOutputStream oos; 
	static int empty;

	
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
		//add in default tag types when user is made
		tagTypes.add("Name");
		tagTypes.add("Location");
		tagTypes.add("Food");
		tagTypes.add("Weather");
	}
	
	public static ArrayList<User> getAllUsers(){
		return allUsers;
	}
	
	public static void write(User u) throws IOException{
		if(empty == 0){
			oos = new ObjectOutputStream (new FileOutputStream(storeDir +File.separator +storeFile));
			oos.writeObject(u);
			empty = 1;
		}
		else{
			oos.writeObject(u);
		}
				
	}
	public static User read()
	           throws IOException, ClassNotFoundException {
	           ObjectInputStream ois = new ObjectInputStream(
	                new FileInputStream(storeDir + File.separator + storeFile));
	           User u = (User)ois.readObject();
	           return u;
	}
	public static void setAllUsers(ArrayList<User> users){
		allUsers = users;
	}
	  
	public static void addUser(User u){
		allUsers.add(u);
	}
	
	public static void deleteUser(String userName){
		User u=null;
		for(User a : allUsers){
			if(a.userName.equals(userName)){
				System.out.println("detele");
				u=a;
			}
		}
		allUsers.remove(u);
	}
	
	public static boolean isInSystem(String userName){
		for(User a : allUsers){
			if(a.userName.equalsIgnoreCase(userName)){
				return true;
			}
		}
		return false;
	}
	
	public static User searchUser(String user, String pass){
		for(User u: allUsers){
			if (u.userName.equalsIgnoreCase(user) && u.password.equals(pass)){
				return u;
			}
		}
		System.out.println("User not in system");
		return null;
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
	
	public void addAlbum(Album album){
		this.albumList.add(album); 
	}
	public void removeAlbum(Album album){
		this.albumList.remove(album); 
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currUser) {
		currentUser = currUser;
	}

	public String getUserName() {
		return userName;
	}
	
	public ArrayList<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(ArrayList<Album> albumList) {
		this.albumList = albumList;
	}
	
	public ArrayList<String> getTagTypes() {
		return tagTypes;
	}

	public void setTagTypes(ArrayList<String> tagTypes) {
		this.tagTypes = tagTypes;
	}


}

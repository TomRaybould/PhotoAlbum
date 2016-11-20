package Model;

import java.util.ArrayList;
import java.io.*;
/**
* User class holds and manipulates information on all of the users in the system
* It can add and delete users, write all user information to memory (file/serilizaton),
* and other user manipulation and checks. It also holds the state of who is the current
* User in the system
* 
* @author  Tom Raybould & Mike Tomkowich
*/
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
	static int newSession;

	/**
	 * Constructor method that takes in a user name and password. Sets default tag types
	 * for the user
	 * @param userName is the User name
	 * @param password is the Password
	 * 
	 * @return User an instance of User
	 */
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
		//add in default tag types when user is made
		tagTypes.add("Name");
		tagTypes.add("Location");
		tagTypes.add("Food");
		tagTypes.add("Weather");
	}
	/**
	 * getter method to get a list of all Current Users
	 *
	 * @return ArrayList<User> list of all current Users
	 */
	public static ArrayList<User> getAllUsers(){
		return allUsers;
	}
	/**
	 * This method creates new file and writes all user data to memory using serialization
	 * @return void
	 */
	public static void write() throws IOException{
		oos = new ObjectOutputStream (new FileOutputStream(storeDir +File.separator +storeFile));
		for(User u : User.getAllUsers()){	
			oos.writeObject(u);
		}
	}
	/**
	 * This reads a serilized file from memory. It is used when login is started
	 * each time.
	 * @return void
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void read() throws IOException, ClassNotFoundException {
		ObjectInputStream oisUser; 
		oisUser = new ObjectInputStream(
	    
        new FileInputStream(storeDir + File.separator + storeFile));
		
		User u;
			try {
				while ((u = (User) oisUser.readObject()) != null) {
					User.addUser(u);
				}
			} catch (EOFException e) {
	
			} finally {
				oisUser.close();
			}
	    
		for(User k: User.getAllUsers()){
			User.setCurrentUser(k);
			//read the albums for that user
			//Album.read();
		}
	}
	/**
	 * method to set an array list that contains all current Users
	 *
	 * @param users ArrayList of all active users
	 *
	 * @return void
	 */
	public static void setAllUsers(ArrayList<User> users){
		allUsers = users;
	}
	/**
	 * method to add a user to list that contains all current users
	 *
	 * @param u the User object that you want to add to list
	 *
	 * @return void
	 */ 
	public static void addUser(User u){
		allUsers.add(u);
	}
	/**
	 * method to delete a user from the list that contains all current users
	 *
	 * @param userName the userName for the User object you would like to delete form lisr
	 *
	 * @return void
	 */
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
	/**
	 * This method checks to see if some user is in the current array of users
	 * returns true if user exists, false if doesnt exist
	 * @param userName the username that is being searched for
	 * @return boolean
	 */
	public static boolean isInSystem(String userName){
		for(User a : allUsers){
			if(a.userName.equalsIgnoreCase(userName)){
				return true;
			}
		}
		return false;
	}
	/**
	 * This method checks to see if some user is in the current array of users
	 * returns the User if the user is in the current user array,
	 * null if user doesnt match any name in user array
	 * @param user the username being searched for
	 * @param pass the password being searched for
	 * @return User the User that has been found
	 */
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
	/**
	 * This method adds an album to the current user's list of albums
	 * @param album the album to be added to the array list
	 * @return void
	 */
	public void addAlbum(Album album){
		this.albumList.add(album); 
		try{
			this.write();
		}catch(Exception e){
			
		}
	}
	/**
	 * This method deletes an album from the current user's list of albums
	 * @param album the album to be added to the array list
	 * @return void
	 */
	public void removeAlbum(Album album){
		this.albumList.remove(album); 
		try{
			this.write();
		}catch(Exception e){
			
		}
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}
	/**
	 * getter method to get current User object
	 *
	 * @return User the current User object
	 */
	public static User getCurrentUser() {
		return currentUser;
	}
	/**
	 * setter method to set current User object
	 *
	 *@param currUser the User you would like to set as current User
	 *
	 * @return void
	 */
	public static void setCurrentUser(User currUser) {
		currentUser = currUser;
	}
	/**
	 * getter method to get a User object's Name
	 *
	 * @return String The User objects userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * getter method to get a list of albums from a User object
	 *
	 * @return ArrayList<Album> The album List
	 */
	public ArrayList<Album> getAlbumList() {
		return albumList;
	}
	/**
	 * setter method to set a list of albums for a User object
	 *
	 *@param albumList the list of albums you would like to set for a User object
	 *
	 * @return void
	 */
	public void setAlbumList(ArrayList<Album> albumList) {
		this.albumList = albumList;
	}
	/**
	 * getter method to get a User's list of tag types
	 *
	 * @return ArrayList<String> a list of a User object's tag types
	 */
	public ArrayList<String> getTagTypes() {
		return tagTypes;
	}
	/**
	 * setter method to set a list of tag types for a User object
	 *
	 *@param tagTypes the list of tags you would like to set for a User object
	 *
	 * @return void
	 */
	public void setTagTypes(ArrayList<String> tagTypes) {
		this.tagTypes = tagTypes;
	}


}

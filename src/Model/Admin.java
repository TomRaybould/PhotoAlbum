package Model;

import java.io.Serializable;
import java.util.ArrayList;
/**
* Admin is a class that is used to add and delete users from the List of all users
* 
* @author  Tom Raybould & Mike Tomkowich
*/
public class Admin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -905990925383463548L;
	private String name;
	private String password;
	private ArrayList<User> listOfUsers = User.getAllUsers();
	
	//this class should be able to add and delete users
	// not much else to this class
	/**
	 * Constructor method that takes in a name and password
	 * @param name is the name of the administrator
	 * @param password is the password for the administrator
	 * 
	 * @return User an instance of Admin
	 */
	public Admin(String name, String password){
		this.name = name;
		this.password = password;
	}
	/**
	 * getter method for name
	 *
	 * @return String returns name of admin
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter method for name
	 *
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter method for password
	 *
	 * @return String returns password of admin
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * setter method for password
	 *
	 * @return void
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * getter method for list of users
	 *
	 * @return ArrayList<User> list of users
	 */
	public ArrayList<User> getListOfUsers() {
		return listOfUsers;
	}
	
	

}

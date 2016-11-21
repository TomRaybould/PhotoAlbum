package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;
/**
* Album used to manipulate data associated with a user's album
* It holds an array of photos for each album, number of photos, the current albums,
* and other fields associated with albums
* 
* @author  Tom Raybould & Mike Tomkowich
*/
public class Album implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7153580835213056212L;
	static public ObjectOutputStream oos;
	private String name;
	private String oldestDate;
	private String latestDate;
	private int numOfPhotos;
	private ArrayList<Photo> photosInAlbum = new ArrayList<Photo>();
	private static Album currentAlbum;
	private static ArrayList<Photo> searchResults;

	// an album will mainly consist of a list of photos
	// it can also have a list of tags, but unclear whether or not that would be necessary
	
	
	/**
	 * getter method for a list of current search results
	 *
	 * @return ArrayList<Photo> The array list of search results
	 */
	public static ArrayList<Photo> getSearchResults() {
		return searchResults;
	}
	/**
	 * setter method for a list of current search results
	 *
	 * @return void
	 */
	public static void setSearchResults(ArrayList<Photo> searchResults) {
		Album.searchResults = searchResults;
	}
	/**
	 * adds a photo to an album instance
	 *
	 * @return void
	 */
	public void addPhotoToAlbum(Photo p){
		photosInAlbum.add(p);
		this.numOfPhotos++;
		return;
		//change number of photos here so we never forget to call a second method
	}
	/**
	 * removes a photo from an album instance
	 *
	 * @return void
	 */
	public void removePhotoFromAlbum(Photo p){
		photosInAlbum.remove(p);
		this.numOfPhotos--;
		return;
		//change number of photos here so we never forget to call a second method
	}
	/**
	 * Constructor method that takes in a String name of the album. 
	 * The instance also holds an array list of photos and the number 
	 * of photos currently in the album
	 * 
	 * @param name is the name of the album
	 * 
	 * @return User an instance of Tag
	 */
	public Album(String name ){
		this.name = name;
		this.photosInAlbum = new ArrayList<Photo>();
		this.numOfPhotos = 0;
	}
	/**
	 * getter method for number of photos in album
	 *
	 * @return int the number of photos in album instance
	 */
	public int getNumOfPhotos(){
		return numOfPhotos;
	}
	
	//Just change count in add and remove
	/**
	 * getter method for name
	 *
	 * @return String returns name of User instance
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter method for name of an album
	 *
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter method for list of photos in an album
	 *
	 * @return ArrayList<Photo> array list of photos in an album instance
	 */
	public ArrayList<Photo> getPhotosInAlbum() {
		return photosInAlbum;
	}
	/**
	 * setter method for array list of photos in an album
	 *
	 * @return void
	 */
	public void setPhotosInAlbum(ArrayList<Photo> photosInAlbum) {
		this.photosInAlbum = photosInAlbum;
	}
	/**
	 * setter method for current album
	 *
	 * @return void
	 */
	public static void setCurrentAlbum(Album A) {
		currentAlbum = A;
	}
	/**
	 * getter method for current album
	 *
	 * @return Album The current album
	 */
	public static Album getCurrentAlbum() {
		return currentAlbum;
	}
	
	public void iterate(){
		ArrayList<Photo> list = this.photosInAlbum;
		for(Photo p: list){
			System.out.print(p);
		}
	}

	@Override
	public String toString() {
		return "Album [name=" + name + ", photosInAlbum=" + photosInAlbum + "]";
	}
	
}

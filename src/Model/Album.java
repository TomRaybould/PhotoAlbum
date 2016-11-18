package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Album implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7153580835213056212L;
	private String name;
	private String oldestDate;
	private String latestDate;
	private int numOfPhotos;
	private ArrayList<Photo> photosInAlbum = new ArrayList<Photo>();
	private static Album currentAlbum;
	private static boolean existingAlbum;
	private static ArrayList<Photo> searchResults;

	// an album will mainly consist of a list of photos
	// it can also have a list of tags, but unclear whether or not that would be necessary
	
	public static ArrayList<Photo> getSearchResults() {
		return searchResults;
	}

	public static void setSearchResults(ArrayList<Photo> searchResults) {
		Album.searchResults = searchResults;
	}
	public void addPhotoToAlbum(Photo p){
		photosInAlbum.add(p);
		this.numOfPhotos++;
		return;
		//change number of photos here so we never forget to call a second method
	}
	public void removePhotoFromAlbum(Photo p){
		photosInAlbum.remove(p);
		this.numOfPhotos--;
		return;
		//change number of photos here so we never forget to call a second method
	}
	
	public Album(String name ){
		this.name = name;
		this.photosInAlbum = new ArrayList<Photo>();
		this.numOfPhotos = 0;
	}
	public int getNumOfPhotos(){
		return numOfPhotos;
	}
	
	//Just change count in add and remove
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Photo> getPhotosInAlbum() {
		return photosInAlbum;
	}

	public void setPhotosInAlbum(ArrayList<Photo> photosInAlbum) {
		this.photosInAlbum = photosInAlbum;
	}
	public static void setCurrentAlbum(Album A) {
		currentAlbum = A;
	}

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

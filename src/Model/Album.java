package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable{
	private String name;
	private ArrayList<Photo> photosInAlbum = new ArrayList<Photo>();
	private static Album currentAlbum;
	// an album will mainly consist of a list of photos
	// it can also have a list of tags, but unclear whether or not that would be necessary
	
	public Album(String name ){
		this.name = name;
		this.photosInAlbum = new ArrayList<Photo>();
	}

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
	public void addPhotoToAlbum(Photo photo){
		photosInAlbum.add(photo);
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

package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable{
	private String name;
	private ArrayList<Photo> photosInAlbum = new ArrayList<Photo>();
	// an album will mainly consist of a list of photos
	// it can also have a list of tags, but unclear whether or not that would be necessary
	
	public Album(String name, ArrayList<Photo> photosInAlbum){
		this.name = name;
		this.photosInAlbum = photosInAlbum;
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
	
}

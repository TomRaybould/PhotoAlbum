package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Photo implements Serializable{
	private String date;
	private String src;
	private ArrayList<Tag> tags;
	private static Photo currentPhoto;
	
	public Photo(String date, String src){
		this.date = date;
		this.tags = new ArrayList<Tag>();
		this.src = src;
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public String getSrc() {
		return src;
	}
	public void addTag(Tag t){
		tags.add(t);
	}
	public static void setCurrentPhoto(Photo p) {
		currentPhoto = p;
	}

	public static Photo getCurrentPhoto() {
		return currentPhoto;
	}
	@Override
	public String toString() {
		return "Photo [date=" + date + ", src=" + src + ", tags=" + tags + "]";
	}
	
	
}

package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Photo implements Serializable{
	private String date;
	private String caption;
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
	
	public void setTags(ArrayList<Tag> tags){
		this.tags = tags;
	}

	public String getSrc() {
		return src;
	}
	public void addTag(Tag t){
		tags.add(t);
	}
	public void deleteTag(Tag t ){
		for(Tag tag: this.getTags()){
			if(t.type.equals(tag.type) && t.value.equals(tag.value)){
				this.getTags().remove(tag);
			}
		}
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
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	
	
}

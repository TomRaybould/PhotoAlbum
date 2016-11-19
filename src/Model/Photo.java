package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.image.Image;

public class Photo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4214429024702902104L;
	private String date;
	private Date calDate;
	private String caption;
	private String src;
	private ArrayList<Tag> tags;
	private static Photo currentPhoto;

	
	public Photo(Date date, String src){
		this.calDate = date;
		this.tags = new ArrayList<Tag>();
		this.src = src;
	}


	public Date getCalDate() {
		return this.calDate;
	}

	public void Date(Date date) {
		this.calDate = date;
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
		try{
			User.write();
		}catch(Exception e){
			
		}
	}
	
	public void removeTag(Tag t ){
		this.getTags().remove(t);
		try{
			User.write();
		}catch(Exception e){
			
		}
	}
	
	public Tag searchTags(String type, String value){
		Tag search =new Tag(type,value);
		for(Tag t : tags){
			if(t.equals(search)){
				return t;
			}
		}
		return null;
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
		try{
			User.write();
		}catch(Exception e){
			
		}
	}

	
	
}

package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.image.Image;
/**
* Photo is used to manipulate data associated with an individual photo
* It holds an array of tags, a name, a date, the source of the photo,
* and the current active photo, as well as other fields associated with photo
* 
* @author  Tom Raybould & Mike Tomkowich
*/
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

	/**
	 * Constructor method that takes in Date and source of where the photo comes from
	 * Also holds an array list of tags that is associated with the instance of the photo
	 * @param date is the Date object that is associated with the photo
	 * @param src is a string that hold the location of the photo
	 * 
	 * @return Photo an instance of Photo
	 */
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

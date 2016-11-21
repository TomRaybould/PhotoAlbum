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
	/**
	 * method to get a calendar date
	 *
	 * @return Date the calendar Date object 
	 */

	public Date getCalDate() {
		return calDate;
	}
	/**
	 * method to set a calendar date
	 *
	 * @param date the Date object you would like to set
	 *
	 * @return void
	 */
	public void Date(Date date) {
		this.calDate = date;
	}
	/**
	 * getter method to get a List of tag objects
	 *
	 * @return ArrayList<Tag> a list of tags
	 */
	public ArrayList<Tag> getTags() {
		return tags;
	}
	/**
	 * setter method for array list tags
	 * 
	 * @param tags list of tags you would like to set
	 * 
	 * @return void
	 */
	public void setTags(ArrayList<Tag> tags){
		this.tags = tags;
	}
	/**
	 * getter method to get a source of a photo object
	 *
	 * @return String the source string
	 */
	public String getSrc() {
		return src;
	}
	/**
	 * method to add a tag to a list of tags
	 *
	 * @param t Tag you would like to add
	 *
	 * @return void
	 */
	public void addTag(Tag t){
		tags.add(t);
		try{
			User.write();
		}catch(Exception e){
			
		}
	}
	/**
	 * method to remove a tag from a list of tags
	 *
	 * @param t Tag you would like to remove
	 *
	 * @return void
	 */
	public void removeTag(Tag t ){
		this.getTags().remove(t);
		try{
			User.write();
		}catch(Exception e){
			
		}
	}
	/**
	 * method searches tag list for a target tag
	 * 
	 * @param type the tag type
	 * 
	 * @param value the tag value
	 *
	 * @return Tag the target tag you are searching for
	 */
	public Tag searchTags(String type, String value){
		Tag search =new Tag(type,value);
		for(Tag t : tags){
			if(t.equals(search)){
				return t;
			}
		}
		return null;
	}
	/**
	 * setter method to set the current photo
	 *
	 *@param p Photo you are setting
	 *
	 * @return void
	 */
	public static void setCurrentPhoto(Photo p) {
		currentPhoto = p;
	}
	/**
	 * getter method to get current Photo
	 *
	 * @return Photo the current photo
	 */
	public static Photo getCurrentPhoto() {
		return currentPhoto;
	}
	@Override
	public String toString() {
		return "Photo [date=" + date + ", src=" + src + ", tags=" + tags + "]";
	}
	/**
	 * getter method to get a caption
	 *
	 * @return String caption
	 */
	public String getCaption() {
		return caption;
	}
	/**
	 * setter method to set a caption
	 * 
	 * @param caption The caption you are setting
	 *
	 * @return void
	 */
	public void setCaption(String caption) {
		this.caption = caption;
		try{
			User.write();
		}catch(Exception e){
			
		}
	}

	
	
}

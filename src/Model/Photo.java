package Model;

import java.util.ArrayList;

public class Photo {
	String date;
	ArrayList<Tag> tags = new ArrayList<Tag>();
	// Photo will consist of a date, name and Image type from javafx
	// image type is not put in here yet because i am still figuring out how to use it
	// examples of sample but non functional code can be seen in login and display photo pages
	// login works with just one photo to start but need to figure out how to change photos still
	// as you will see, clicking on a button will bring you to folder, but still not sure how to set image
	// if you go to login page and uncomment out the 2 commented lines, a picture of snow will appear
	// if you leave the comment out, you will be able to select a picture from folders that you have stored
	public Photo(String date, ArrayList<Tag> tags){
		this.date = date;
		this.tags = tags;
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

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	
}

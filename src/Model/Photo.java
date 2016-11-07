package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Photo implements Serializable{
	private String date;
	private String src;
	private ArrayList<Tag> tags = new ArrayList<Tag>();
	
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

	public String getSrc() {
		return src;
	}
	
}

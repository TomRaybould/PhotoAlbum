package Model;

import java.io.Serializable;

public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2028385128694426315L;
	String type;
	String value;


	// tag will consist of a type and value
	// such as location : NJ
	// a list of types should not be put in here because
	// each user will have its own list
	// so put that list in user
	public Tag(String type, String value){
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	@Override
	public String toString() {
		return "Tag [type=" + type + ", value=" + value + "]";
	}
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Tag)){
			return false;
		}
		Tag t = (Tag)o;
	
		if(this.type.equals(t.type)&&(this.value.equals(t.value))){
			return true;
		}
		else{
			return false;
		}
	}
}

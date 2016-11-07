package Model;

public class Tag {
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
	
}

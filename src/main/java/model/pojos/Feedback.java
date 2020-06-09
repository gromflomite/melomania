package model.pojos;

public class Feedback {
	
	private String type;
	private String text;

	public Feedback() {
		super();
		this.type = "";
		this.text = "";
	}

	public Feedback(String type, String text) {
		this();
		this.type = type;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Feedback [type=" + type + ", text=" + text + "]";
	}

}

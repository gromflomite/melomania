package model.pojos;

public class Album {

	private int id;
	private String title;
	private String artist;
	private int year;
	private String comments;
	private String cover;

	// Default constructor
	public Album() {
		this.id = 0;
		this.title = "";
		this.artist = "";
		this.year = 2020;
		this.comments = "One of the greatest albums of all time.";
		this.cover = "https://i.imgur.com/bo3KIgS.jpg";
	}

	public Album(int id, String title, String artist, int year, String comments, String cover) {
		this();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.comments = "One of the greatest albums of all time.";
		this.cover = "https://i.imgur.com/bo3KIgS.jpg";
	}

	public Album(int id) {
		this();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", artist=" + artist + ", year=" + year + ", comments="
				+ comments + ", cover=" + cover + "]";
	}

}

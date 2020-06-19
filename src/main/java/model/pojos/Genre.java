package model.pojos;

public class Genre {

    private int id;
    private String genre;

    // Default constructor
    public Genre() {
	super();
	this.id = 0;
	this.genre = "";
    }

    // Getters and Setters
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getGenre() {
	return genre;
    }

    public void setGenre(String genre) {
	this.genre = genre;
    }

    // toString()
    @Override
    public String toString() {
	return "Genre [id=" + id + ", genre=" + genre + "]";
    }

}

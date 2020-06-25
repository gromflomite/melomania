package model.pojos;

import java.util.ArrayList;

public class Genre {

    private int id;
    private String genre;
    private ArrayList<Album> albums;

    // Default constructor
    public Genre() {
	super();
	this.id = 0;
	this.genre = "";
	this.albums = new ArrayList<Album>();
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

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    // toString()
    @Override
    public String toString() {
	return "Genre [id=" + id + ", genre=" + genre + "]";
    }

}

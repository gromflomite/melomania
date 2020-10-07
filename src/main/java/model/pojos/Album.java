package model.pojos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Album {

    private int id;

    @NotBlank(message = "Album title is necessary")
    private String title;

    @Size(min = 2, message = "The Artist name must have at least 2 characters")
    @NotBlank(message = "Artist name is necessary")
    private String artist;

    @NotNull
    @Min(value = 1900, message = "Publishing year must be greater than 1900")
    @Max(value = 2025, message = "Publishing year must not be greater than 2025")
    private int year;

    private String comments;

    private String cover;

    private String dateApproved;

    private User user; // Adding the User (userId) object

    private Genre genre; // Adding the Genre object

    // Default constructor
    // --------------------------------------------------------------------
    public Album() {
	this.id = 0;
	this.title = "";
	this.artist = "";
	this.year = 2020;
	this.comments = "One of the greatest albums of all time.";
	this.cover = "https://i.imgur.com/bo3KIgS.jpg";
	this.dateApproved = "";
	this.user = new User();
	this.genre = new Genre();
    }

    public Album(int id, String title, String artist, int year, String comments, String cover, Genre genre) {
	this();
	this.id = id;
	this.title = title;
	this.artist = artist;
	this.year = year;
	this.comments = "One of the greatest albums of all time.";
	this.cover = "https://i.imgur.com/bo3KIgS.jpg";
	this.dateApproved = "";
	this.user = new User();
	this.genre = new Genre();
    }

    // Getters and Setters
    // --------------------------------------------------------------------
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

    public String getDateApproved() {
	return dateApproved;
    }

    public void setDateApproved(String dateApproved) {
	this.dateApproved = dateApproved;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Genre getGenre() {
	return genre;
    }

    public void setGenre(Genre genre) {
	this.genre = genre;
    }

    // toString()
    // -----------------------------------------------------------------------------
    @Override
    public String toString() {
	return "Album [id=" + id + ", title=" + title + ", artist=" + artist + ", year=" + year + ", comments=" + comments + ", cover=" + cover + ", dateApproved=" + dateApproved + ", user=" + user + ", genre=" + genre + "]";
    }

}

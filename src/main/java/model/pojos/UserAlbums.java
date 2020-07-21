package model.pojos;

/** 
 * POJO to manage the number of approved and pending albums *
 */

public class UserAlbums {

    int id_user;
    int userAlbumsApproved;
    int userAlbumsPending;

    public UserAlbums() {
	super();
    }

    public UserAlbums(int id_user, int userAlbumsApproved, int userAlbumsPending) {
	this.id_user = id_user;
	this.userAlbumsApproved = userAlbumsApproved;
	this.userAlbumsPending = userAlbumsPending;
    }

    public int getId_user() {
	return id_user;
    }

    public void setId_user(int id_user) {
	this.id_user = id_user;
    }

    public int getUserAlbumsApproved() {
	return userAlbumsApproved;
    }

    public void setUserAlbumsApproved(int userAlbumsApproved) {
	this.userAlbumsApproved = userAlbumsApproved;
    }

    public int getUserAlbumsPending() {
	return userAlbumsPending;
    }

    public void setUserAlbumsPending(int userAlbumsPending) {
	this.userAlbumsPending = userAlbumsPending;
    }

    @Override
    public String toString() {
	return "UserAlbums [id_user=" + id_user + ", userAlbumsApproved=" + userAlbumsApproved + ", userAlbumsPending=" + userAlbumsPending + "]";
    }

}

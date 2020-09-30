package model.daos;

import java.sql.SQLException;
import java.util.ArrayList;

import model.pojos.Album;
import model.pojos.UserAlbums;

public interface AlbumDao {

    public void validateAlbum(int id);
    
    public ArrayList<Album> getByGenre(int idGenre);

    public ArrayList<Album> getLast(int numAlbums);

    public ArrayList<Album> getAll();

    public ArrayList<Album> getAllbyUser(int idUser, boolean areApproved);

    public UserAlbums getUserAlbumsDbView(int userId);

    public Album getById(int albumId) throws Exception;

    public Album getByIdCheckingUser(int idAlbum, int idUser) throws Exception;

    public Album insert(Album newAlbum) throws Exception;

    public Album update(Album updateAlbum) throws Exception;

    public Album updateCheckingUser(Album editAlbum, int idUser);

    public Album delete(int deleteAlbumId) throws Exception;

    public void deleteCheckingUser(int idAlbum, int idUser) throws Exception, SQLException;    

}

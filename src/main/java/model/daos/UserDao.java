package model.daos;

import java.util.ArrayList;

import model.pojos.User;

public interface UserDao {

    public ArrayList<User> getAll();

    public User getById(int idUser);

    public User insert(User user) throws Exception;

    public User checkLogin(String userName, String userPassword); 
    
    public User update(User userUpdate) throws Exception;
    
}
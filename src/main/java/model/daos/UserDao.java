package model.daos;

import java.util.ArrayList;

import model.pojos.User;

/**
 * 
 * Interfaz que contiene los métodos relacionados con la gestión de los usuarios de la aplicación.
 * 
 * @author Caronte
 * @version 1.0
 *
 */
public interface UserDao {

    /**     
     * Recupera todos los usuarios existentes en la BD
     * 
     * @return ArrayList<> de tipo User con los usuarios existentes en la BD
     */
    public ArrayList<User> getAll();

    /**     
     * Recupera el usuario que coincida con la ID enviada como parámetro
     * 
     * @param idUser integer con el número de ID que buscamos 
     * @return objeto tipo User con los valores del mismo
     */
    public User getById(int idUser);

    /**     
     * Crea una nueva entrada en la BD con los valores de User que le pasemos. 
     * 
     * @param user objeto User() 
     * @return boolean true si la llamada a la BD termina correctamente. false si ha encontrado un problema
     * @throws Exception lanzada en caso de problemas con la BD
     */
    public boolean insert(User user) throws Exception;

    /**
     * Comprueba contra la BD la existencia de un usuario y, si existe, recupera todos los valores
     * asociados a dicho usuario.
     * 
     * @param userName el nombre que el usuario ha introducido en el formulario de inicio de sesión
     * @param userPassword la contraseña introducida por el usuario en el form. de inicio de sesión
     * @return User objeto de tipo User() con todos los valores recuperados de la BD
     */
    public User checkLogin(String userName, String userPassword); 
    
    /** 
     * Actualiza un usuario existente en la BD. 
     * 
     * @param userUpdate objeto tipo User() conteniendo los valores que queremos actualizar en la BD
     * @return el mismo objeto User() recibido
     * @throws Exception si aparece un problema con la llamada a la BD
     */
    public User update(User userUpdate) throws Exception;
    
    /**
     * Busca un usuario por su nombre. Retorna un boolean true si el usuario existe y false en caso contrario.
     * 
     * @param userName String con el valor del nombre del usuario a buscar
     * @return boolean true si se encuentra un usuario con ese nombre, false si no lo encuentra 
     */
    public boolean searchByName(String userName);
    
}
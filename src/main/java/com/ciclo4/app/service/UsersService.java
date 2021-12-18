package com.ciclo4.app.service;

import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Users;
import com.ciclo4.app.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repositorio;

    /**
     * Devuelve todos los usuarios de la base de datos
     * @return
     */
    public List<Users> listAllUsers(){
        return repositorio.listAll();
    }
    
    /**
     * Añade un Usuario a la base de Datos
     * @param user
     * @return
     */
    public Optional<Users> insertUser(Users user){
        return repositorio.postUser(user);
    }

    public Optional<Users> getUserById(Integer id){
        return repositorio.getUserbyId(id);
    }

    /**
     * Busca un usuario por el Correo
     * @param email
     * @return
     */
    public Optional<Users> getUserByEmail(String email){
        return repositorio.getUser(email);
    }
    public List<Users> getUserByMonth(String month) {
        return repositorio.getUserByMonth(month);
    }
    /**
     * Busca un usuario por el Nombre
     * @param name
     * @return
     
    public Optional<User> getUserByName(String name){
        return repositorio.ge(name);
    }*/

    /**
     * Valida si un usuario existe
     * @param user_email
     * @param user_password
     * @return
     */
    public Users checkAuth(String email, String password) {
        return repositorio.checkUser(email, password);
    }
    /**
     * Actualiza Usuario
     * @param user
     * @return
     */
    public Users updateUser(Users user){
        return repositorio.putUser(user);
    }
    public void deleteUser(Integer id) {
        repositorio.deleteUser(id);
    }
    public void deleteUsers(List<Integer> id) {
        repositorio.deleteUsers(id);
    }
    /**
     * Borra todos los usuarios de la base de datos
     */
    public void deleteAll(){
        repositorio.deleteAll();;
    }
}

package com.ciclo4.app.controller;

import java.util.List;
import java.util.Optional;

import com.ciclo4.app.ListaIds;
import com.ciclo4.app.model.Users;
import com.ciclo4.app.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService servicio;


    /**
     * Servicios GET
     * 
     */
    /** */
    @GetMapping("")
    public String demo(){
        return "<h2>String Test</h2>";
    }
    @GetMapping("/all")
    public List<Users> demo2(){
        return servicio.listAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUser(@PathVariable Integer id){
        return servicio.getUserById(id);
    }
    @GetMapping("emailexist/{email}")
    public boolean getUser(@PathVariable String email){
        System.out.println(servicio.getUserByEmail(email));
        if(servicio.getUserByEmail(email).isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    @GetMapping("/birthday/{month}")
    public List<Users> getMonth(@PathVariable String month){
        return servicio.getUserByMonth(month);
    }
    @GetMapping("/{user_email}/{user_password}")
    public Users getAuth(@PathVariable String user_email,@PathVariable String user_password){
        return servicio.checkAuth(user_email, user_password);
    }
    /**
     * Servicios POST
     * 
     */
    /** */
    /**
     * 
     * @param user
     * @return
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public Optional<Users> save(@RequestBody Users user){
        return servicio.insertUser(user);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Users update(@RequestBody Users user){
        return servicio.updateUser(user);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        servicio.deleteUser(id);
    }
    @DeleteMapping("/ids")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody ListaIds lista) {
        servicio.deleteUsers(lista.getLista());
    }
    /**
     * 
     */
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        servicio.deleteAll();
    }
}

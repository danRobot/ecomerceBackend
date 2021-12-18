package com.ciclo4.app.controller;

import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Clothes;
import com.ciclo4.app.service.ClothesService;

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
@RequestMapping("/api/clothe")
@CrossOrigin
public class ClothesController {
    @Autowired
    private ClothesService servicio;

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
    public List<Clothes> demo2(){
        return servicio.listAllClothes();
    }
    @GetMapping("/{reference}")
    public Optional<Clothes> reference(@PathVariable String reference){
        return servicio.getByReference(reference);
    }
    @GetMapping("/price/{price}")
    public List<Clothes> price(@PathVariable Integer price){
        return servicio.getByPrice(price);
    }

    @GetMapping("/description/{description}")
    public List<Clothes> description(@PathVariable String description){
        return servicio.getbyDescription(description);
    }
    
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public Optional<Clothes> save(@RequestBody Clothes user){
        return servicio.insertClothe(user);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Clothes update(@RequestBody Clothes user){
        return servicio.updateUser(user);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        servicio.deleteUser(id);
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

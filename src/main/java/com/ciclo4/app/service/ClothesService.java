package com.ciclo4.app.service;

import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Clothes;
import com.ciclo4.app.repository.ClothesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClothesService {
    @Autowired
    private ClothesRepository repositorio;

    /**
     * Devuelve todos los vestidos de la base de datos
     * @return
     */
    public List<Clothes> listAllClothes(){
        return repositorio.listAll();
    }
    
    /**
     * Añade un vestido a la base de Datos
     * @param clothe
     * @return
     */
    public Optional<Clothes> insertClothe(Clothes clothe){
        return repositorio.postClothes(clothe);
    }
     /**
     * Busca un vestido por el Correo
     * @param reference
     * @return
     */
    public List<Clothes> getbyDescription(String description) {
        return repositorio.getByDescription(description);
    }
    public List<Clothes> getByPrice(Integer price){
        return repositorio.getByPrice(price);
    }
    /**
     * Actualiza Usuario
     * @param user
     * @return
     */
    public Clothes updateUser(Clothes user){
        return repositorio.putClothes(user);
    }
    public void deleteUser(String reference) {
        repositorio.deleteClothes(reference);
    }
    /**
     * Borra todos los usuarios de la base de datos
     */
    public void deleteAll(){
        repositorio.deleteAll();;
    }

    public Optional<Clothes> getByReference(String reference) {
        return repositorio.getByReference(reference);
    }
}

package com.ciclo4.app.repository.crud;

import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Clothes;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClothesCrudRepository extends MongoRepository<Clothes,Integer> {
    
    /**
     * 
     * @param reference
     * @return
     */
    @Query("{reference:?0}")
    public Optional<Clothes> getClothesByReference(String reference);
    /**
     * 
     * @param description
     * @return
     */
    @Query("{'description':{$regex:?0,$options:'i'}}")
    public List<Clothes> getClothesByDescription(String description);
    /**
     * 
     * @param price
     * @return
     */
    @Query("{'price':?0}")
    List<Clothes> getByPrice(Integer price);
}

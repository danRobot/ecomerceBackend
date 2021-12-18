package com.ciclo4.app.repository.crud;

import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UsersCrudRepository extends MongoRepository<Users,Integer> {
    /**
     * 
     * @param email
     * @return
     */
    @Query("{email:?0}")
    Optional<Users> getUserByEmail(String email);
    /**
     * 
     * @param email
     * @param password
     * @return
     */
    @Query("{email:?0,password:?1}")
    Optional<Users> checkUser(String email,String password);
    /**
     * 
     * @param name
     * @return
     */
    @Query("{name:?0}")
    List<Users> getUserByName(String name);
    /**
     * 
     * @param idf
     * @return
     */
    @Query("{identification:?0}")
    Optional<Users> getUserByidentification(String idf);
    /**
     * 
     * @param month
     * @return
     */
    @Query("{monthBirthtDay:?0}")
    List<Users> getUserByMonthBirthDay(String month);
}

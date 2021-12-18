package com.ciclo4.app.repository.crud;

import java.util.Date;
import java.util.List;

import com.ciclo4.app.model.Orders;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrdersCrudRepository extends MongoRepository<Orders,Integer>{
    /**
     * 
     * @param zona
     * @return
     */
    @Query("{'salesMan.zone':?0}")
    List<Orders> getOrdersByZone(String zona);
    /**
     * 
     * @param id
     * @return
     */
    @Query("{'salesMan._id':?0}")
    List<Orders> getOrdersBySalesman(Integer id);
    /**
     * 
     * @param date
     * @param id
     * @return
     */
    @Query("{$and:[{'registerDay':?0},{'salesMan._id':?1}]}")
    List<Orders> getOrdersByDateAndSalesman(Date date,Integer id);
    /**
     * 
     * @param status
     * @param id
     * @return
     */
    @Query("{$and:[{'status':?0},{'salesMan._id':?1}]}")
    List<Orders> getOrdersByStatus(String status,Integer id);
}

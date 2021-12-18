package com.ciclo4.app.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Orders;
import com.ciclo4.app.repository.crud.OrdersCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersRepository {
    //private static final String COLLECTION = "orders";
    @Autowired
    private OrdersCrudRepository repository;
    /*@Autowired
    private MongoTemplate mongoTemplate;*/

    public List<Orders> listAll() {
        return repository.findAll();
    }

    public Optional<Orders> getClothe(Integer id) {
        return repository.findById(id);
    }
    /*private long checkExistence(String key,String property) {
        Query query = new Query();
        query.addCriteria(Criteria.where(key).is(property));
        return mongoTemplate.count(query, COLLECTION);
    }
    private Orders getBykey(String key,String property) {
        Query query = new Query();
        query.addCriteria(Criteria.where(key).is(property));
        return mongoTemplate.findOne(query, Orders.class);
    }*/
    public List<Orders> getOrdersByZone(String zona){
        return repository.getOrdersByZone(zona);
    }
    public List<Orders> getOrdersBySalesman(Integer id){
        return repository.getOrdersBySalesman(id);
    }
    public List<Orders> getOrdersByDate(Date date,Integer id){
        return repository.findByRegisterDayAndSalesManId(date, id);
    }
    public List<Orders> getOrdersByStatus(String status,Integer id){
        return repository.getOrdersByStatus(status, id);
    }
    /**
     * Operacion POST
     * @param order
     * @return
     */
    public Optional<Orders> postOrders(Orders order){
        Optional<Orders> o=null;
        int id= order.getId();
        o=repository.findById(id);
        if (o.isEmpty()) {
            if (order.getId()==null) {
                List<Orders> all=repository.findAll();
                int maxId=0;
                for (Orders ord : all) {
                    if(ord.getId()>maxId){
                        maxId=ord.getId();
                    }
                }
                order.setId(maxId+1);
            }
            return Optional.of(repository.insert(order));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Operacion PUT
     * @param Orders
     * @return
     */
    public Orders putOrders(Orders Orders) {
        Integer id=Orders.getId();
        Optional<Orders> users=repository.findById(id);
        if (users.isEmpty()!=true) {
            Orders old=users.get();
            if(Orders.getProducts()==null){
                Orders.setProducts(old.getProducts());
            }
            if(Orders.getQuantities()==null){
                Orders.setQuantities(old.getQuantities());
            }
            if(Orders.getRegisterDay()==null){
                Orders.setRegisterDay(old.getRegisterDay());
            }
            if(Orders.getSalesMan()==null){
                Orders.setSalesMan(old.getSalesMan());
            }
            if(Orders.getStatus()==null){
                Orders.setStatus(old.getStatus());
            }
            return repository.save(Orders);
        } else {
            return new Orders();
        }
    }
    /**
     * Operacion delete
     * @param user
     * @return
     */
    public void deleteOrder(Integer id) {
        Optional<Orders> op=repository.findById(id);
        if(op.isEmpty()){
            return;
        }else{
            repository.delete(op.get());
        }
    }
    /**
     * Limpia colección de Usuarios
     */
    public void deleteAll(){
        repository.deleteAll();;
    }
}

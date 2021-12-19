package com.ciclo4.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Orders;
import com.ciclo4.app.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository repositorio;

    /**
     * Devuelve todos los vestidos de la base de datos
     * @return
     */
    public List<Orders> listAllOrders(){
        return repositorio.listAll();
    }

    /**
     * Añade un vestido a la base de Datos
     * @param order
     * @return
     */
    public Optional<Orders> insertOrder(Orders order){
        return repositorio.postOrders(order);
    }

    /**
     * Busca un vestido por el Correo
     * @param reference
     * @return
     */
    public Optional<Orders> getOrderById(Integer reference){
        return repositorio.getClothe(reference);
    }
    public List<Orders> getOrderByZone(String zona){
        return repositorio.getOrdersByZone(zona);
    }
    public List<Orders> getOrdersBySalesman(Integer id){
        return repositorio.getOrdersBySalesman(id);
    }
    public List<Orders> getOrdersByDate(Date date,Integer id){
        /* CODIGO QUE SIRVE PARA DEPURAR
        List<Orders> allOrders=repositorio.listAll();
        List<Orders> query=new ArrayList<Orders>();
        int val1;
        for (Orders orders : allOrders) {
            val1=orders.getRegisterDay().compareTo(date);
            System.out.println("###### DATE ######");
            System.out.println(date);
            System.out.println(orders.getRegisterDay());
            System.out.println("###### ID ######");
            System.out.println(orders.getSalesMan().getId());
            System.out.println("###### ID ######");
            if (val1==0 && orders.getSalesMan().getId()==id) {
                query.add(orders);
            }
        }
        System.out.println(query);
        System.out.println(repositorio.getOrdersByDate(date, id));
        return query;
        */
        return repositorio.getOrdersByDate(date, id);
    }
    public List<Orders> getOrdersByStatus(String status,Integer id){
        return repositorio.getOrdersByStatus(status, id);
    }
    /**
     * Actualiza Usuario
     * @param order
     * @return
     */
    public Orders updateOrder(Orders order){
        return repositorio.putOrders(order);
    }
    public void deleteOrder(Integer id) {
        repositorio.deleteOrder(id);
    }
    /**
     * Borra todos los usuarios de la base de datos
     */
    public void deleteAll(){
        repositorio.deleteAll();;
    }
}

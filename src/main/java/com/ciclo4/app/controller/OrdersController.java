package com.ciclo4.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Orders;
import com.ciclo4.app.service.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/api/order")
@CrossOrigin
public class OrdersController {
    @Autowired
    private OrdersService servicio;

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
    public List<Orders> demo2(){
        return servicio.listAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Orders> getOrder(@PathVariable Integer id){
        return servicio.getOrderById(id);
    }
    @GetMapping("/zona/{zona}")
    public List<Orders> getOrderbyZone(@PathVariable String zona){
        return servicio.getOrderByZone(zona);
    }
    @GetMapping("/salesman/{id}")
    public List<Orders> getOrderbySalesman(@PathVariable Integer id){
        return servicio.getOrdersBySalesman(id);
    }
    @GetMapping("/date/{date}/{id}")
    public List<Orders> getOrderbySalesman(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@PathVariable Integer id){
        System.out.println("AAAAAAAAAAA");
        System.out.println(date);
        System.out.println("AAAAAAAAAAA");
        return servicio.getOrdersByDate(date, id);
    }
    @GetMapping("/state/{state}/{id}")
    public List<Orders> getOrderbySalesman(@PathVariable String state,@PathVariable Integer id){
        return servicio.getOrdersByStatus(state, id);
    }
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public Optional<Orders> save(@RequestBody Orders order){
        return servicio.insertOrder(order);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Orders update(@RequestBody Orders order){
        return servicio.updateOrder(order);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        servicio.deleteOrder(id);
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

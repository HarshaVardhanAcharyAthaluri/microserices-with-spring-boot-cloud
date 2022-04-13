package com.training.orderservice.conterollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.orderservice.dao.OrderRepository;
import com.training.orderservice.model.Orders;

@RestController
public class OrderController {

	
	@Autowired
	OrderRepository repo;
	
	@GetMapping("/greet")
	public String greetFromOrderf() {
		return "Hello From OrderService";
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Orders>>  getOrders(){
		
		return new ResponseEntity<List<Orders>>((List<Orders>) repo.findAll(),HttpStatus.OK);		
	}
	
	@PostMapping("/order/addorder")
	public ResponseEntity<Orders> saveOrder(@RequestBody Orders order){
		return new ResponseEntity<Orders>(repo.save(order),HttpStatus.CREATED);
		
	}

	@GetMapping("/order/{userId}")
	public ResponseEntity<List<Orders>> getOrderByUserId(@PathVariable Integer userId){
		 repo.findByuserid(userId).stream().forEach(x->x.setStatus("Instance one"));
		
		return new ResponseEntity<List<Orders>>(repo.findByuserid(userId), HttpStatus.OK);
	}
	
	
}
























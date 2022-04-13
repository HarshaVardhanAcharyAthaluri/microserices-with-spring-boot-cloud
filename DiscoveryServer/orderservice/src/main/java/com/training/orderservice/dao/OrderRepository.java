package com.training.orderservice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.training.orderservice.model.Orders;

public interface OrderRepository extends CrudRepository<Orders, Integer>{
	public List<Orders> findByuserid(Integer userid);
}

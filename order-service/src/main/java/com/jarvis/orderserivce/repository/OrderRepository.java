package com.jarvis.orderserivce.repository;

import com.jarvis.orderserivce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

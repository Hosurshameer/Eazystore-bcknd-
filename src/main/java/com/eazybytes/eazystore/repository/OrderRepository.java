package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer );

    List<Order> findByOrderStatus(String orderStatus);

    @Query("select 0 from Order o where o.customer=:customer order by o.createdAt desc")
    List<Order> findOrderByCustomer(@Param("customer") Customer customer );



}

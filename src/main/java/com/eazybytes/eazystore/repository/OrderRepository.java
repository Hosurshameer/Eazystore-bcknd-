package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer );

    List<Order> findByOrderStatus(String orderStatus);
//this is called jpql(java persistence query language)query which operates on the entity and filelds of entity
    @Query("select o from Order o where o.customer=:customer order by o.createdAt desc")
    List<Order> findOrderByCustomer(@Param("customer") Customer customer );


//we can use ?1 as a placeholder for the first parameter. this is called positional parameter
//    @Query("select o from Order o where o.customer=?1 order by o.createdAt desc")
//    List<Order> findOrderByCustomer(@Param("customer") Customer customer );


//    this is called native sql query which directly operates on the database
//    @Query(value = "select * from orders o where o.customer_id=:customerId order by o.created_at desc",nativeQuery = true)
//    List<Order> findOrderByCustomer(@Param("customerId") Long customerId);

    @Transactional
    @Modifying
    @Query("update Order o set o.orderStatus=:orderStatus,o.updatedAt=CURRENT_TIMESTAMP,o.updatedBy=:updatedBy where o.orderId=:orderId")
    int updateOrderStatus(@Param("orderId")Long orderId, @Param("orderStatus")String orderStatus,@Param("updatedBy") String updatedBy);



}

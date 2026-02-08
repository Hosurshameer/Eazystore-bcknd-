package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.constants.ApplicationConstants;
import com.eazybytes.eazystore.dto.OrderItemDto;
import com.eazybytes.eazystore.dto.OrderRequestDto;
import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.entity.Order;
import com.eazybytes.eazystore.entity.OrderItem;
import com.eazybytes.eazystore.entity.Product;
import com.eazybytes.eazystore.exception.ResourceNotFoundException;
import com.eazybytes.eazystore.repository.OrderRepository;
import com.eazybytes.eazystore.repository.ProductRepository;
import com.eazybytes.eazystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class OrderServiceImpl implements IOrderService {
    private  final OrderRepository orderRepository;
    private  final ProductRepository productRepository;
    private final ProfileServiceImpl profileServiceImpl;

    @Override
    public void createOrder(OrderRequestDto orderRequestDto) {

       Customer customer= profileServiceImpl.getAuthenticatedCustomer();

       Order order=new Order();
        BeanUtils.copyProperties(orderRequestDto, order);
        order.setCustomer(customer);
        order.setOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);
        List<OrderItem> orderItems=orderRequestDto.orderItems().stream().map(orderItemDto -> {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            Product product=productRepository.findById(orderItemDto.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product","ProductID",orderItemDto.productId().toString()));

            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemDto.quantity());
            orderItem.setPrice(orderItemDto.price());
            return  orderItem;


        }).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        orderRepository.save(order);


    }
}

package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.constants.ApplicationConstants;
import com.eazybytes.eazystore.dto.OrderItemDto;
//import com.eazybytes.eazystore.dto.OrderItemReponseDto;
import com.eazybytes.eazystore.dto.OrderItemResponseDto;
import com.eazybytes.eazystore.dto.OrderRequestDto;
import com.eazybytes.eazystore.dto.OrderResponseDto;
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

    @Override
    public List<OrderResponseDto> getCustomerOrders() {
        Customer customer=profileServiceImpl.getAuthenticatedCustomer();
        List<Order>orders=orderRepository.findByCustomerOrderByCreatedAtDesc(customer);
        return orders.stream().map(this::mapToOrderResponseDto).collect(Collectors.toList());

    }

    @Override
    public List<OrderResponseDto> getAllPendingOrders() {
       List<Order> orders=orderRepository.findByOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);
        return orders.stream().map(this::mapToOrderResponseDto).collect(Collectors.toList());
    }

    @Override
    public Order updateOrderStatus(Long orderId, String orderStatus) {
       Order order=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order","OrderId",orderId.toString()));
       order.setOrderStatus(orderStatus);
       return orderRepository.save(order);
    }


    public OrderResponseDto mapToOrderResponseDto(Order order){
        List<OrderItemResponseDto>itemDTOs=order.getOrderItems().stream().map(this::mapToOrderItemResponseDTO).collect(Collectors.toList());
        OrderResponseDto orderResponseDto=new OrderResponseDto(order.getOrderId(),order.getOrderStatus(),order.getTotalPrice(),order.getCreatedAt().toString(),itemDTOs);
        return orderResponseDto;
    }


    public  OrderItemResponseDto mapToOrderItemResponseDTO(OrderItem orderItem){
        OrderItemResponseDto itemDTO=new OrderItemResponseDto(orderItem.getProduct().getImageUrl(),orderItem.getProduct().getName(),orderItem.getQuantity(),orderItem.getPrice());
        return itemDTO;
    }


}

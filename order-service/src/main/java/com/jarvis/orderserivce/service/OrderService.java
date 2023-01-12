package com.jarvis.orderserivce.service;

import com.jarvis.orderserivce.dto.OrderLineItemsDto;
import com.jarvis.orderserivce.dto.OrderRequest;
import com.jarvis.orderserivce.model.Order;
import com.jarvis.orderserivce.model.OrderLineItems;
import com.jarvis.orderserivce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =  orderRequest.getOrderLineItemsDtoList()
                .stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItems);

        //Call inventory service, and place order if product is in stock

        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}

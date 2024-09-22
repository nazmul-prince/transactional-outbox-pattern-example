package com.outbox.service;

import com.outbox.common.dto.OrderRequestDTO;
import com.outbox.common.mapper.OrderDTOtoEntityMapper;
import com.outbox.common.mapper.OrderEntityToOutboxEntityMapper;
import com.outbox.constant.OrderEvent;
import com.outbox.order.entity.Order;
import com.outbox.entity.Outbox;
import com.outbox.order.repository.OrderRepository;
import com.outbox.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderDTOtoEntityMapper orderDTOtoEntityMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private OrderEntityToOutboxEntityMapper orderEntityToOutboxEntityMapper;


    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {

        Order order = orderDTOtoEntityMapper.map(orderRequestDTO);
        order = orderRepository.save(order);

        Outbox outbox = orderEntityToOutboxEntityMapper.map(order);
        outbox.setOrderEvent(OrderEvent.CREATED);
        outboxRepository.save(outbox);

        return order;
    }

    @Transactional
    public Order updateOrder(Integer id, OrderRequestDTO orderRequestDTO) {

        Order order = orderDTOtoEntityMapper.map(orderRequestDTO);
        order.setId(id);
        order = orderRepository.save(order);

        Outbox outbox = orderEntityToOutboxEntityMapper.map(order);
        outbox.setOrderEvent(OrderEvent.UPDATED);
        outboxRepository.save(outbox);

        return order;
    }
}

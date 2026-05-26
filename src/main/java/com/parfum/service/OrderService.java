package com.parfum.service;

import com.parfum.dto.OrderDTO.*;
import com.parfum.model.*;
import com.parfum.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PerfumeRepository perfumeRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        PerfumeRepository perfumeRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.perfumeRepository = perfumeRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(CreateOrderRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Order order = new Order();
        order.setUser(user);
        order.setPaymentMethod(request.getPaymentMethod());
        order.setDeliveryAddress(request.getDeliveryAddress());

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemReq : request.getItems()) {
            Perfume perfume = perfumeRepository.findById(itemReq.getPerfumeId())
                    .orElseThrow(() -> new RuntimeException("Perfume não encontrado."));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setPerfume(perfume);
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(perfume.getPrice());

            order.getItems().add(item);
            total = total.add(perfume.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity())));
        }

        order.setTotal(total);
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return orderRepository.findByUserId(user.getId());
    }

    public Order processPayment(PaymentRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        // Simulação de processamento de pagamento
        order.setStatus(Order.OrderStatus.PAID);
        return orderRepository.save(order);
    }
}
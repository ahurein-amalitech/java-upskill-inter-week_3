package org.example.orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserProxy userProxy;

    @GetMapping
    public Page<Order> getOrders(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @PostMapping
    public Object createOrder(@RequestBody Order order) {
        Optional<User> user = userProxy.getUserById(order.getCustomerId());
        if (user.isEmpty()) {
            return "User not found";
        }
        return orderRepository.save(order);
    }
}

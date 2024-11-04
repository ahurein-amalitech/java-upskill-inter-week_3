package org.example.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserProxy {
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id);
}

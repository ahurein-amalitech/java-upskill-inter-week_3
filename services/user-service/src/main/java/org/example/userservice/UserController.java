package org.example.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public Page<User> getAllUsers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
}

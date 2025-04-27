package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService service;

    @Autowired
    public AdminController (UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> showAllUsers() {
        List <User> allUsers = service.getList();
        return allUsers;
    }

    @GetMapping("/{id}")
    public User getUserById (@PathVariable Long id){
        User users = service.read(id);
        return users;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        service.create(user, user.getRoles().toArray(new String[0]));
        return ResponseEntity.ok(user);
    }
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        service.update(user, user.getRoles().toArray(new String[0]));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping ("/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.delete(getUserById(id));
        return "User deleted";
    }

}

package com.jeremymendez.sportfinal.Controller;

import com.jeremymendez.sportfinal.model.User;
import com.jeremymendez.sportfinal.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsers();

    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        // Validación de campos vacíos
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre(firstname) está vacío");
        }
        if (user.getLastname() == null || user.getLastname().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo apellido(lastname) está vacío");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo email está vacío");
        }
        // Validación de tipo de correo
        String email = user.getEmail().toLowerCase();
        if (!(email.endsWith("@gmail.com") || email.endsWith("@kinal.edu.gt") || email.endsWith("@yahoo.com"))) {
            return ResponseEntity.badRequest().body("Correo invalido, los correos permitidos son gmail, kinal.edu.gt y yahoo.com");
        }
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            if (u.getFirstName().equalsIgnoreCase(user.getFirstName())) {
                return ResponseEntity.badRequest().body("Nombre: No esta permitido utilizar un nombre ya existente");
            }
            if (u.getLastname().equalsIgnoreCase(user.getLastname())) {
                return ResponseEntity.badRequest().body("Apellido: No esta permitido utilizar un apellido ya existente");
            }
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                return ResponseEntity.badRequest().body("El email ya está en uso");
            }
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public User updateUser (@PathVariable Integer id, @RequestBody User user){
        return userService.updateUser(id, user);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}


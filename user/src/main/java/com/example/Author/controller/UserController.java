package com.example.Author.controller;

import com.example.Author.model.User;
import com.example.Author.repository.UserRepository;
import com.example.Author.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAll();
//    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }


    @GetMapping("/{userName}")
    public User getUserById(@PathVariable String userName) {
        return userRepository.findByUserName(userName);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody User user) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userName= authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public  ResponseEntity<?> deleteUserbyId(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        //userRepository.deleteByUserName(authentication.getName());
        return  new ResponseEntity<>("HELLO"+authentication.getName(),HttpStatus.OK
        );
    }


}
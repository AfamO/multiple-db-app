/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afam.app.multipledb.controllers;

import com.afam.app.multipledb.dao.product.ProductRepository;
import com.afam.app.multipledb.dao.user.UserProfile;
import com.afam.app.multipledb.dao.user.UserRepository;
import com.afam.app.multipledb.model.product.Product;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author afam.okonkwo
 */

@RestController
public class RestResource {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ProductRepository productRepository;
    
    @RequestMapping("/api/users/me")
    public ResponseEntity<UserProfile> profile() {
        
        // Build some data to return for testing
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@gmail.com";
        
        UserProfile profile = new UserProfile();
        profile.setEmail(email);
        profile.setName(user.getUsername());
        
        return ResponseEntity.ok(profile);
        
    }
    
        @PostMapping("/createUser")
    public com.afam.app.multipledb.model.user.User createUser(@RequestBody com.afam.app.multipledb.model.user.User user){
        return userRepository.save(user);
    }

    @GetMapping("/getUser/{userId}")
    public com.afam.app.multipledb.model.user.User getUser(@PathVariable Integer userId){
        return userRepository.getOne(userId);
    }
    @GetMapping("/getUsers")
    public List<com.afam.app.multipledb.model.user.User> getUsers(){
        System.out.println("Request Made for getUsers");
        return userRepository.findAll();
    }
    @GetMapping("/user/me")
    public UserProfile user(Principal principal) {
        String email = principal.getName() + "@gmail.com";
        
        UserProfile profile = new UserProfile();
        profile.setEmail(email);
        profile.setName(principal.getName());
        System.out.println("Request Made for user "+principal.getName());
        return profile;
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
    
}

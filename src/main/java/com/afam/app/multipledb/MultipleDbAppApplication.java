package com.afam.app.multipledb;

import com.afam.app.multipledb.dao.product.ProductRepository;
import com.afam.app.multipledb.dao.user.UserRepository;
import com.afam.app.multipledb.model.product.Product;
import com.afam.app.multipledb.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MultipleDbAppApplication {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(MultipleDbAppApplication.class, args);
	}
        
@PostMapping("/createUser")
public User createUser(@RequestBody User user){
    return userRepository.save(user);
}

@PostMapping("/createProduct")
public Product createProduct(@RequestBody Product product){
    return productRepository.save(product);
}

}

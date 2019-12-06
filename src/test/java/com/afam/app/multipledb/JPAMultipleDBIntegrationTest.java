package com.afam.app.multipledb;

import com.afam.app.multipledb.config.ProductConfig;
import com.afam.app.multipledb.config.UserConfig;
import com.afam.app.multipledb.dao.user.UserRepository;
import com.afam.app.multipledb.dao.product.ProductRepository;
import com.afam.app.multipledb.model.product.Product;
import com.afam.app.multipledb.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserConfig.class, ProductConfig.class})
@SpringBootTest(classes = MultipleDbAppApplication.class)
@EnableTransactionManagement
public class JPAMultipleDBIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional("userTransactionManager")
    public  void whenCreatingUser_thenCreated(){
        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);
        user = userRepository.save(user);
         System.out.println("The userId == "+user.getId());
        assertTrue(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUsersWithSameEmail_thenRollback() {
        User user1 = new User();
        user1.setName("John");
        user1.setEmail("john@test.com");
        user1.setAge(20);
        user1 = userRepository.save(user1);
        assertTrue(userRepository.findById(user1.getId()).isPresent());

        User user2 = new User();
        user2.setName("Tom");
        user2.setEmail("john@test.com");
        user2.setAge(10);
        try {
            user2 = userRepository.save(user2);
            userRepository.flush();
            fail("DataIntegrityViolationException should be thrown!");
        } catch (DataIntegrityViolationException e) {
        }


    }

    @Test
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {
        Product product = new Product();
        product.setName("Book");
        product.setId(2);
        product.setPrice(20);
        product = productRepository.save(product);
        System.out.println("The productId == "+product.getId());

        assertTrue(productRepository.findById(product.getId()).isPresent());
    }
}

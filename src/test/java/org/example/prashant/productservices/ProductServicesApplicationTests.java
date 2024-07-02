package org.example.prashant.productservices;

import org.example.prashant.productservices.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServicesApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testingQueries() {
        productRepository.findAllByCategoryTitle("iOS");
    }

}

package org.example.prashant.productservices.Repositories;

import org.example.prashant.productservices.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);
}

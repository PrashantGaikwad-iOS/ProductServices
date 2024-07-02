package org.example.prashant.productservices.Repositories;

import org.example.prashant.productservices.Models.Category;
import org.example.prashant.productservices.Models.Product;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);
    @Override
    List<Product> findAll();
    Product findByIdIs(Long productId);
    List<Product> findAllByCategoryTitle(String title);
    @Override
    void deleteById(Long aLong);
}

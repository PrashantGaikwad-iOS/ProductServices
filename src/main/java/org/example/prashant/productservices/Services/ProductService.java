package org.example.prashant.productservices.Services;

import org.example.prashant.productservices.Models.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    Page<Product> getAllProductsByPage(Integer pageSize, Integer pageNumber, String sort);
    Product createProduct(String title,
                          String description,
                          String category,
                          double price,
                          String image);
    List<String> getAllCategories();
    Product updateProduct(Long productId,
                          String title,
                          String description,
                          String category,
                          double price,
                          String image);

    List<Product> getAllProductsForCategory(String category);
    void deleteProduct(Long productId);
}

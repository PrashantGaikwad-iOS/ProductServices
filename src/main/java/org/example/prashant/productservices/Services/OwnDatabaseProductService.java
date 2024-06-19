package org.example.prashant.productservices.Services;

import org.example.prashant.productservices.Models.Category;
import org.example.prashant.productservices.Models.Product;

import java.util.List;

public class OwnDatabaseProductService implements ProductService{
    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        return null;
    }

    @Override
    public String[] getAllCategories() {
        return null;
    }

    @Override
    public Product updateProduct(Long productId,
                                 String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        return null;
    }

    @Override
    public List<Product> getAllProductsForCategory(String category) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
    }
}

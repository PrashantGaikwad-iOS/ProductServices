package org.example.prashant.productservices.Controllers;

import org.example.prashant.productservices.DTOs.CreateProductRequestDTO;
import org.example.prashant.productservices.Models.Product;
import org.example.prashant.productservices.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService ps;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.ps = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO request) {
        return ps.createProduct(request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage());
    }
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId) {
        return ps.getSingleProduct(productId);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = ps.getAllProducts();
        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
        return response;
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> getAllProductsForCategory(@PathVariable("category") String category) {
        List<Product> products = ps.getAllProductsForCategory(category);
        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
        return response;
    }
    @GetMapping("/categories")
    public ResponseEntity<String[]> getAllCategories() {
        String[] categories = ps.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId,
                                 @RequestBody CreateProductRequestDTO request) {
        return ps.updateProduct(productId,
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage());
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        ps.deleteProduct(productId);
//        return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
    }
}


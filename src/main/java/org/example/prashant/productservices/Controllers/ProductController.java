package org.example.prashant.productservices.Controllers;

import org.example.prashant.productservices.DTOs.CreateProductRequestDTO;
import org.example.prashant.productservices.Models.Category;
import org.example.prashant.productservices.Models.Product;
import org.example.prashant.productservices.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.OK);
        return response;
    }

    @GetMapping("/productsByPage/{pageSize}/{pageNumber}/{sort}")
    public ResponseEntity<List<Product>> getAllProductsByPage(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("sort") String sort) {
        Page<Product> products = ps.getAllProductsByPage(pageSize, pageNumber, sort);
        return new ResponseEntity<>(products.getContent(), HttpStatus.OK);
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> getAllProductsForCategory(@PathVariable("category") String category) {
        List<Product> products = ps.getAllProductsForCategory(category);
        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.OK);
        return response;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories= ps.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
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
    }
}


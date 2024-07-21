package org.example.prashant.productservices.Services;

import org.example.prashant.productservices.DTOs.FakeStoreProductDTO;
import org.example.prashant.productservices.DTOs.SelfDBProductDTO;
import org.example.prashant.productservices.Models.Category;
import org.example.prashant.productservices.Models.Product;
import org.example.prashant.productservices.Repositories.CategoryRepository;
import org.example.prashant.productservices.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return productRepository.findByIdIs(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllProductsByPage(Integer pageSize, Integer pageNumber, String sort) {
        Pageable pageable = null;

        if(sort != null) {
            pageable = PageRequest.of(pageNumber,pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return productRepository.findAll(pageable);
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Category categoryFromDB = categoryRepository.findCategoriesByTitle(category);
        if(categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);
//            categoryFromDB = categoryRepository.save(newCategory);
            categoryFromDB = newCategory;
        }

        product.setCategory(categoryFromDB);

        Product newProduct = productRepository.save(product);

        return newProduct;
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categoris =  categoryRepository.findAll();
        List<String> titles = new ArrayList<>(categoris.size());
        for (Category object : categoris) {
            titles.add(object.getTitle());
        }
        return titles;
    }

    @Override
    public Product updateProduct(Long productId,
                                 String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        SelfDBProductDTO selfDBProductDTO = new SelfDBProductDTO();
        selfDBProductDTO.setTitle(title);
        selfDBProductDTO.setDescription(description);
        selfDBProductDTO.setCategory(category);
        selfDBProductDTO.setPrice(price);
        selfDBProductDTO.setImage(image);

        productRepository.save(selfDBProductDTO.toProduct());

        return selfDBProductDTO.toProduct();
    }

    @Override
    public List<Product> getAllProductsForCategory(String category) {
        return productRepository.findAllByCategoryTitle(category);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

package org.example.prashant.productservices.Services;

import org.example.prashant.productservices.Models.Category;
import org.example.prashant.productservices.Models.Product;
import org.example.prashant.productservices.Repositories.CategoryRepository;
import org.example.prashant.productservices.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

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

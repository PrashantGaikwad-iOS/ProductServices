package org.example.prashant.productservices.Services;

import org.example.prashant.productservices.DTOs.FakeStoreProductDTO;
import org.example.prashant.productservices.Models.Category;
import org.example.prashant.productservices.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public  FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDTO fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDTO.class);

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] fakeStoreProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class
                );

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProducts) {
            products.add(fakeStoreProductDTO.toProduct());
        }

        return products;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setImage(image);

        FakeStoreProductDTO response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductDTO,
                FakeStoreProductDTO.class
                );

        return response.toProduct();
    }

    @Override
    public String[] getAllCategories() {
        String[] categories = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );
        return categories;
    }

    @Override
    public Product updateProduct(Long productId,
                                 String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setImage(image);

        restTemplate.put(
                "https://fakestoreapi.com/products/" + productId,
                fakeStoreProductDTO,
                FakeStoreProductDTO.class
        );

        return fakeStoreProductDTO.toProduct();
    }

    @Override
    public List<Product> getAllProductsForCategory(String category) {
        FakeStoreProductDTO[] fakeStoreProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,
                FakeStoreProductDTO[].class
        );

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProducts) {
            products.add(fakeStoreProductDTO.toProduct());
        }

        return products;
    }

    @Override
    public void deleteProduct(Long productId) {
        restTemplate.delete(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDTO.class
        );
    }
}

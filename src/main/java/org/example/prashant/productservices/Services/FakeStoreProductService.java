package org.example.prashant.productservices.Services;

import org.example.prashant.productservices.DTOs.FakeStoreProductDTO;
import org.example.prashant.productservices.Exceptions.ProductNotFoundException;
import org.example.prashant.productservices.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public  FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        FakeStoreProductDTO fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDTO.class);

        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with given Id not found. Please try some other productId");
        }

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
    public Page<Product> getAllProductsByPage(Integer pageSize, Integer pageNumber, String sort) {
        return null;
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
    public List<String> getAllCategories() {
        String[] categories = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );
        return List.of(categories);
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

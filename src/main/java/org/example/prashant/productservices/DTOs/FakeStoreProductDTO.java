package org.example.prashant.productservices.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.prashant.productservices.Models.Category;
import org.example.prashant.productservices.Models.Product;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);
        Category productCategory = new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);

        return product;
    }
}

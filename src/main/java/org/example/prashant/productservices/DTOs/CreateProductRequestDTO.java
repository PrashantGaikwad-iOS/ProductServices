package org.example.prashant.productservices.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}

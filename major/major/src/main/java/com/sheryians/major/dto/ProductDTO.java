package com.sheryians.major.dto;



import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int categoryId;
    private double price;
    private double AvailableNumber;
    private String description;
    private String imageName;
}

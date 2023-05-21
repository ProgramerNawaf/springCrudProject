package com.example.blog.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    @NotNull(message = "id should not be null")
    @Min(value = 3, message = "id must be at least 3 length ")
    private int id;
    @NotNull(message = "id should not be null")
    @Size(min = 3, message = "name must be at least 3 length ")
    private String name;
    @NotNull(message = "price should not be null")
    @PositiveOrZero
    private double price;
    @NotNull(message = "categoryId should not be null")
    @Min(value = 3, message = "categoryId must be at least 3 length ")
    private int categoryId;

}

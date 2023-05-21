package com.example.blog.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MerchantStock {
    @NotNull(message = "id should not be null")
    @Min(value = 3, message = "id must be at least 3 length ")
    private int id;
    @NotNull(message = "productId should not be null")
    @Min(value = 3, message = "productId must be at least 3 length ")
    private int productId;
    @NotNull(message = "merchantId should not be null")
    @Min(value = 3, message = "merchantId must be at least 3 length ")
    private int merchantId;
    @NotNull(message = "merchantId should not be null")
    @Min(value = 10, message = "Stock must have at least 10 prducts! ")
    private int Stock;



}

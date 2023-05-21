package com.example.blog.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Category {
    @NotNull(message = "id should not be null")
    @Min(value = 3, message = "id must be at least 3 length ")
    private int id;
    @NotNull(message = "id should not be null")
    @Size(min = 3, message = "name must be at least 3 length ")
    private String name;
}

package com.example.blog.Controller;

import com.example.blog.Model.Category;
import com.example.blog.Model.Product;
import com.example.blog.Service.CategoryService;
import com.example.blog.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProducts(){
        return ResponseEntity.status(200).body( productService.getProducts());
    }
    @PostMapping("/create")
    public ResponseEntity postProduct(@Valid @RequestBody Product product , Errors e ){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(categoryService.search(product.getCategoryId()) == -1) {
            return ResponseEntity.status(400).body("Category with this ID dosent exist!");
        }
        if(productService.search(product.getId()) != -1) {
            return ResponseEntity.status(400).body("Product with this ID already exist!");
        }

        productService.postProduct(product);
        return ResponseEntity.status(200).body("Product added");


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@Valid @RequestBody Product product , Errors e , @PathVariable int id){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(categoryService.search(product.getCategoryId()) == -1) {
            return ResponseEntity.status(400).body("Category with this ID dosent exist!");
        }
        if(productService.search(product.getId()) == -1) {
            return ResponseEntity.status(400).body("Product with this ID dosent exist!");
        }
            productService.updateProduct(product,id);
            return ResponseEntity.status(200).body("product updated");


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
        if(productService.deleteProdcut(id))
            return ResponseEntity.status(200).body("Product deleted");

        return ResponseEntity.status(400).body("Product with this ID dosent exist!");
    }
}

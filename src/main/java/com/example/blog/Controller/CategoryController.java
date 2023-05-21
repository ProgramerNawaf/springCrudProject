package com.example.blog.Controller;

import com.example.blog.Model.Category;
import com.example.blog.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategories(){
        return ResponseEntity.status(200).body( categoryService.getCategories());
    }
    @PostMapping("/create")
    public ResponseEntity postCategory(@Valid @RequestBody Category category , Errors e ){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(categoryService.postCategory(category))
            return ResponseEntity.status(200).body("Category added");

        return ResponseEntity.status(400).body("Category with this ID already exist!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@Valid @RequestBody Category category , Errors e , @PathVariable int id){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(categoryService.updateCategory(category,id))
            return ResponseEntity.status(200).body("Category updated");

        return ResponseEntity.status(400).body("Category with this ID dosent exist!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
        if(categoryService.deleteCategory(id))
            return ResponseEntity.status(200).body("Category deleted");

        return ResponseEntity.status(400).body("Category with this ID dosent exist!");
    }
}

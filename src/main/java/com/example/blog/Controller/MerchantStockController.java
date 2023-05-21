package com.example.blog.Controller;

import com.example.blog.Model.Category;
import com.example.blog.Model.Merchant;
import com.example.blog.Model.MerchantStock;
import com.example.blog.Service.MerchantService;
import com.example.blog.Service.MerchantStockService;
import com.example.blog.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    private final MerchantService merchantService;
    private final ProductService productService;



    @GetMapping("/get")
    public ResponseEntity getMerchantStocks(){
        return ResponseEntity.status(200).body( merchantStockService.getMerchantStocks());
    }
    @PostMapping("/create")
    public ResponseEntity postMerchantStock (@Valid @RequestBody MerchantStock merchantStock , Errors e ){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if(merchantStockService.search(merchantStock.getId()) != -1)
            return ResponseEntity.status(400).body("Merchant Stock with this Id already exist!");
        if(merchantService.search(merchantStock.getMerchantId()) == -1)
            return ResponseEntity.status(400).body("Merchant with this Id dosent exist!");
        if(productService.search(merchantStock.getProductId()) == -1)
            return ResponseEntity.status(400).body("Product with this Id dosent exist!");


        merchantStockService.postMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("Merchant stock added");


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@Valid @RequestBody MerchantStock merchantStock , Errors e , @PathVariable int id){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if(merchantService.search(merchantStock.getMerchantId()) == -1)
            return ResponseEntity.status(400).body("Merchant with this Id dosent exist!");
        if(productService.search(merchantStock.getProductId()) == -1)
            return ResponseEntity.status(400).body("Product with this Id dosent exist!");

            merchantStockService.updateMerchant(merchantStock, id);
            return ResponseEntity.status(200).body("Merchant Stock updated");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){
        if(merchantStockService.deleteMerchantStock(id))
            return ResponseEntity.status(200).body("Merchant Stock deleted");

        return ResponseEntity.status(400).body("Merchant Stock with this ID dosent exist!");
    }
}

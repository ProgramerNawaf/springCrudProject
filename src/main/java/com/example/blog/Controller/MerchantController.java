package com.example.blog.Controller;

import com.example.blog.Model.Category;
import com.example.blog.Model.Merchant;
import com.example.blog.Model.MerchantStock;
import com.example.blog.Service.MerchantService;
import com.example.blog.Service.MerchantStockService;
import com.example.blog.Service.ProductService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getMerchants(){
        return ResponseEntity.status(200).body( merchantService.getMerchants());
    }
    @PostMapping("/create")
    public ResponseEntity postMerchant (@Valid @RequestBody Merchant merchant , Errors e ){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(merchantService.postMerchant(merchant))
            return ResponseEntity.status(200).body("Merchant added");

        return ResponseEntity.status(400).body("Merchant with this ID already exist!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@Valid @RequestBody Merchant merchant , Errors e , @PathVariable int id){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(merchantService.updateMerchant(merchant,id))
            return ResponseEntity.status(200).body("Merchant updated");

        return ResponseEntity.status(400).body("Merchant with this ID dosent exist!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){
        if(merchantService.deleteMerchant(id))
            return ResponseEntity.status(200).body("Merchant deleted");

        return ResponseEntity.status(400).body("Merchant with this name dosent exist!");
    }

    @PutMapping("/addStock")
    public ResponseEntity addStock (@RequestBody ObjectNode json  , Errors e){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

                if(merchantStockService.search(json.get("merchantStockId").asInt()) == -1)
            return ResponseEntity.status(400).body("Merchant Stock with this Id dosent exist!");
        if(merchantStockService.searchStock(json.get("productId").asInt() ,json.get("merchantId").asInt() ) == -1)
            return ResponseEntity.status(400).body("Merchant Stock with this productId and merchantId dosent exist");


        merchantStockService.addStock(merchantStockService.search(json.get("merchantStockId").asInt()), json.get("Stock").asInt());

        return ResponseEntity.status(200).body("Merchant stock updated");


    }
}

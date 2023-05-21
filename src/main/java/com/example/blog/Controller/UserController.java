package com.example.blog.Controller;
import com.example.blog.Model.Category;
import com.example.blog.Model.User;
import com.example.blog.Service.MerchantStockService;
import com.example.blog.Service.ProductService;
import com.example.blog.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body( userService.getUsers());
    }
    @PostMapping("/create")
    public ResponseEntity postUser(@Valid @RequestBody User user , Errors e ){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(userService.postUser(user))
            return ResponseEntity.status(200).body("User added");

        return ResponseEntity.status(400).body("User with this user name or ID already exist!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@Valid @RequestBody User user , Errors e , @PathVariable int id){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(userService.updateUser(user,id))
            return ResponseEntity.status(200).body("User updated");

        return ResponseEntity.status(400).body("User with this ID dosent exist!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
        if(userService.deleteUser(id))
            return ResponseEntity.status(200).body("User deleted");

        return ResponseEntity.status(400).body("User with this ID dosent exist!");
    }

    @PutMapping("/buyProduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@RequestBody int number ,Errors e , @PathVariable("userId") int userId,@PathVariable("productId") int productId ,@PathVariable("merchantId") int merchantId){
        if(e.hasErrors()){
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(userService.search(userId) == -1)
            return ResponseEntity.status(400).body("User with this id dosent exist!");
        if(merchantStockService.searchStock(productId , merchantId) == -1)
            return ResponseEntity.status(400).body("Stock with this merchant and product dosent exist!");

        if(merchantStockService.minusStock(merchantStockService.searchStock(productId , merchantId) , number))
            if( userService.setBalance(userId,productService.getPrice(productId,number)))
                return ResponseEntity.status(200).body("Purchase is done!");
            else
                return ResponseEntity.status(400).body("User dosent have enoguh balance");
        else
                return ResponseEntity.status(400).body("Not enoguh stock to complete purchae!");


    }

}

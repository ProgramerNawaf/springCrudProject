package com.example.blog.Service;

import com.example.blog.Model.Product;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Component
public class ProductService {
    ArrayList<Product> products = new ArrayList<Product>();

    public ArrayList<Product> getProducts(){
        return products;
    }

    public boolean postProduct(Product user){
            products.add(user);
            return true;
    }

    public boolean updateProduct(Product product , int id){
        if( search(id) == -1)
            return false;
        products.set(search(id),product);
        return true;
    }

    public boolean deleteProdcut(int id){
        if(search(id) == -1)
            return false;
        products.remove(search(id));
        return true;
    }

    public int search(int id){
        for(int i = 0 ; i<products.size();i++)
            if(products.get(i).getId() == id)
                return i;
        return -1;
    }
    public double getPrice(int id , int number){
       return products.get(search(id)).getPrice() * number;
    }
}

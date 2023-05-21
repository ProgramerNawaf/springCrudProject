package com.example.blog.Service;



import com.example.blog.Model.Merchant;
import com.example.blog.Model.MerchantStock;
import com.example.blog.Model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Component
public class MerchantStockService {

    ArrayList <MerchantStock> merchants = new ArrayList<MerchantStock>();

    public ArrayList<MerchantStock> getMerchantStocks(){
        return merchants;
    }

    public boolean postMerchantStock(MerchantStock merchant){
            merchants.add(merchant);
            return true;
    }

    public boolean updateMerchant(MerchantStock merchant , int id){
        if( search(id) == -1)
            return false;
        merchants.set(search(id),merchant);
        return true;
    }

    public boolean deleteMerchantStock(int id){
        if(search(id) == -1)
            return false;
        merchants.remove(search(id));
        return true;
    }

    public int search(int id){
        for(int i = 0 ; i<merchants.size();i++)
            if(merchants.get(i).getId()== id)
                return i;
        return -1;
    }

    public int searchStock(int productId, int merchantId){
        for(int i = 0 ; i<merchants.size();i++)
            if(merchants.get(i).getProductId()== productId && merchants.get(i).getMerchantId()== merchantId )
                return i;
        return -1;
    }

    public void addStock (int id , int number){
        merchants.get(id).setStock(merchants.get(id).getStock() + number);
    }

    public boolean minusStock(int id , int number){
        if(merchants.get(id).getStock() >= number) {
            merchants.get(id).setStock(merchants.get(id).getStock() - number);
            return true;
        }
        return false;

    }




}

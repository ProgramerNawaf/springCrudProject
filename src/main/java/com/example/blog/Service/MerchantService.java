package com.example.blog.Service;



import com.example.blog.Model.Merchant;
import com.example.blog.Model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Component
public class MerchantService {

    ArrayList <Merchant> merchants = new ArrayList<Merchant>();

    public ArrayList<Merchant> getMerchants(){
        return merchants;
    }

    public boolean postMerchant(Merchant merchant){
        if(search(merchant.getId()) == -1) {
            merchants.add(merchant);
            return true;
        }
        return false;
    }

    public boolean updateMerchant(Merchant merchant , int id){
        if( search(id) == -1)
            return false;
        merchants.set(search(id),merchant);
        return true;
    }

    public boolean deleteMerchant(int id){
        if(search(id) == -1)
            return false;
        merchants.remove(search(id));
        return true;
    }

    public int search( int id){
        for(int i = 0 ; i<merchants.size();i++)
            if(merchants.get(i).getId() == id)
                return i;
        return -1;
    }




}

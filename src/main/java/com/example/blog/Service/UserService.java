package com.example.blog.Service;



import com.example.blog.Model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Component
public class UserService {

    ArrayList <User> users = new ArrayList<User>();

    public ArrayList<User> getUsers(){
        return users;
    }

    public boolean postUser(User user){
        if(search(user.getId()) == -1 && searchByUsername(user.getUserName()) == -1) {
            users.add(user);
            return true;
        }
        return false;
    }

    public boolean updateUser(User user , int id){
        if( search(id) == -1)
            return false;
        users.set(search(id),user);
        return true;
    }

    public boolean deleteUser(int id){
        if(search(id) == -1)
            return false;
        users.remove(search(id));
        return true;
    }

    public int search(int id){
        for(int i = 0 ; i<users.size();i++)
            if(users.get(i).getId() == id)
                return i;
        return -1;
    }
    public int searchByUsername(String name){
        for(int i = 0 ; i<users.size();i++)
            if(users.get(i).getUserName().equalsIgnoreCase(name))
                return i;
        return -1;
    }

    public boolean setBalance(int userId ,double price){
        if(users.get(search(userId)).getBalance() >= price) {
            users.get(search(userId)).setBalance(users.get(search(userId)).getBalance() - price);
            return true;
        }
        return false;
    }




}

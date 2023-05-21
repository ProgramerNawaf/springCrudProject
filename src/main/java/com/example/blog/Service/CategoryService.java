package com.example.blog.Service;


import com.example.blog.Model.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Component
public class CategoryService {

    ArrayList <Category> categories = new ArrayList<Category>();

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public boolean postCategory(Category category){
        if(search(category.getId()) == -1) {
            categories.add(category);
            return true;
        }
        return false;
    }

    public boolean updateCategory(Category category , int id){
        if( search(id) == -1)
            return false;
        categories.set(search(id),category);
        return true;
    }

    public boolean deleteCategory(int id){
        if(search(id) == -1)
            return false;
        categories.remove(search(id));
        return true;
    }

    public int search(int id){
        for(int i = 0 ; i<categories.size();i++)
            if(categories.get(i).getId()== id)
                return i;
        return -1;
    }




}

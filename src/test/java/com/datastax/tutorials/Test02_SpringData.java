package com.datastax.tutorials;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.tutorials.service.category.CategoryEntity;
import com.datastax.tutorials.service.category.CategoryRepository;

@SpringBootTest
public class Test02_SpringData {
    
    @Autowired
    CategoryRepository catRepo;
    
    @Test
    public void show_toplevelItems() {
        List<CategoryEntity> categories = catRepo.findByKeyParent("toplevel");
        Assertions.assertEquals(4, categories.size());
        System.out.println("Categories:");
        categories.stream().forEach(c -> {
            System.out.println("- " + c.getKey().getName() + " with hildren:" + c.getChildren());
        });
    }
    

}

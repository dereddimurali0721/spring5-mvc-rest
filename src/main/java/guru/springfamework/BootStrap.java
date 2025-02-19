package guru.springfamework;

import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        Category fruits= new Category();
        fruits.setName("Fruits");

        Category dried= new Category();
        dried.setName("Dried");

        Category fresh= new Category();
        fresh.setName("Fresh");

        Category exotic= new Category();
        exotic.setName("Exotic");

        Category nuts= new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println(categoryRepository.findAll().size());
    }
}

package guru.springfamework;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/v2/categories/"))
public class CategoryController {

    CategoryService categoryService;
    CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){
        return new ResponseEntity<CategoryListDTO>
                (new CategoryListDTO(categoryService.fetchAllCategories()), HttpStatus.OK);
    }


    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String name){
        System.out.println(name);
        return new ResponseEntity<CategoryDTO>(categoryService.findByName(name),HttpStatus.OK);
    }
}

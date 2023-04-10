package guru.springfamework;

import guru.springfamework.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> fetchAllCategories();
    CategoryDTO findByName(String name);
}

package guru.springfamework;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.config.CategoryMapper;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    CategoryRepository categoryRepository;

    CategoryServiceImpl(CategoryMapper categoryMapper,CategoryRepository categoryRepository){
        this.categoryMapper=CategoryMapper.CATEGORY_MAPPER;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<CategoryDTO> fetchAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByName(String name) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.findByName(name));
    }
}

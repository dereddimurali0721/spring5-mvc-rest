package guru.springfamework;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.config.CategoryMapper;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest extends TestCase {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        categoryService= new CategoryServiceImpl(CategoryMapper.CATEGORY_MAPPER,categoryRepository);
    }
    @Test
    public void testFetchAllCategories() {

        List<Category> categories= Arrays.asList(new Category(),new Category(),new Category());
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        assertEquals(3,categoryService.fetchAllCategories().size());
    }

    @Test
    public void testFindByName() {

        Category category= new Category();
        category.setName("Dereddy");
        Mockito.when(categoryRepository.findByName(ArgumentMatchers.anyString())).thenReturn(category);
        assertEquals("Dereddy",categoryService.findByName("D").getName());

    }


}

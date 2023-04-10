package guru.springfamework;

import guru.springfamework.api.v1.model.CategoryDTO;
import junit.framework.TestCase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest extends TestCase {

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    MockMvc mockMvc;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        categoryController= new CategoryController(categoryService);
        mockMvc=MockMvcBuilders.standaloneSetup(categoryController).build();
    }
    @Test
    public void testGetAllCategories() throws Exception {

        CategoryDTO categoryDTO= new CategoryDTO();
        categoryDTO.setName("D");

        CategoryDTO categoryDTO1= new CategoryDTO();
        categoryDTO.setName("M");

        CategoryDTO categoryDTO2= new CategoryDTO();
        categoryDTO.setName("R");

        List<CategoryDTO>   categoryDTOS= Arrays.asList(categoryDTO,categoryDTO1,categoryDTO2);

        Mockito.when(categoryService.fetchAllCategories()).thenReturn(categoryDTOS);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/categories/")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.categories", IsCollectionWithSize.hasSize(3)));
    }

    @Test
    public void testGetCategory()throws Exception{
        CategoryDTO categoryDTO= new CategoryDTO();
        categoryDTO.setName("Dereddy");
        Mockito.when(categoryService.findByName(ArgumentMatchers.anyString())).thenReturn(categoryDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/categories/k")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.equalTo("Dereddy")));

    }

}

package guru.springfamework.spring5mvcrest;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.config.CategoryMapper;
import guru.springfamework.domain.Category;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Spring5MvcRestApplicationTests {

	@Test
	public void contextLoads() {

		Category category= new Category();
		category.setName("Test");

		CategoryDTO categoryDTO = CategoryMapper.CATEGORY_MAPPER.categoryToCategoryDto(category);


		Assertions.assertThat(categoryDTO).isNotNull();
		System.out.println(categoryDTO.getName());
	}



}

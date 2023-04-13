package guru.springfamework.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by jt on 9/24/17.
 */
@Data
public class CategoryDTO {
    private Long id;
    @Schema(description = "This is customer's name",defaultValue = "Murali",required = true)
    private String name;
}

package guru.springfamework.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestControllerTest {
    public static String jsonConverter(Object o) throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(o);
    }
}

package guru.springfamework.advice;

import guru.springfamework.ExceptionHandler.ResourceNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ResourceNotFound.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest webRequest)
    {
        return new ResponseEntity<Object>("Resource Not Found!!!!",new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}

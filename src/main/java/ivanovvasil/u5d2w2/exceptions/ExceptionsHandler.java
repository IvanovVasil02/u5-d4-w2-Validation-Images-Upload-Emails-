package ivanovvasil.u5d2w2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionsHandler {
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErorrsPayload handleBadRequest(BadRequestException e) {
    return new ErorrsPayload(e.getMessage(), new Date());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErorrsPayload handleNotFound(NotFoundException e) {
    return new ErorrsPayload(e.getMessage(), new Date());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErorrsPayload handleGeneric(Exception e) {
    e.printStackTrace();
    return new ErorrsPayload("we are sorry at the moment we have some internal problems, we are trying to resolve them", new Date());
  }
}

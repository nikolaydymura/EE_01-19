package edu.ee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidBookIdException extends Exception {
  public InvalidBookIdException(int userId) {
    super("Invalid book id " + userId +". Book Id must be greater than 0.");
  }
}

package rs.ac.uns.ftn.devops.tim5.agentreport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rs.ac.uns.ftn.devops.tim5.agentreport.dto.ErrorMessageDTO;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessageDTO> handleException(Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(e.getMessage(), badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(errorMessageDTO, badRequest);
    }
}

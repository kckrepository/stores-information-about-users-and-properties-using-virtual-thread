package org.andi.dani.error;

import org.andi.dani.dtos.CreatedListingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BindException.class})
    protected ResponseEntity<CreatedListingDto> handleBindException(BindException ex) {
        var createdListingDto = new CreatedListingDto();
        createdListingDto.setResult(false);
        return new ResponseEntity<CreatedListingDto>(createdListingDto, HttpStatus.BAD_REQUEST);
    }
}

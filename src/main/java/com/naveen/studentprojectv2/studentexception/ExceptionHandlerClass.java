package com.naveen.studentprojectv2.studentexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> dataException(DataNotFoundException exception) {
        return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UnableToUpdateException.class)
    public ResponseEntity<Object> updateException(
            UnableToUpdateException unableToUpdateException) {
        return new ResponseEntity<>("Unable to update the student details", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = UnableToDeleteException.class)
    public ResponseEntity<Object> DeleteException(UnableToDeleteException unableToDeleteException) {
        return new ResponseEntity<>("unable to delete the student details", HttpStatus.BAD_REQUEST);
    }

}

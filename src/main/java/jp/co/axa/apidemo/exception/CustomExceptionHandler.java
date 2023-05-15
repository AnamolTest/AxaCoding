package jp.co.axa.apidemo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class CustomExceptionHandler {
	
	 private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	 
	 public ResponseEntity<String> handleInvalidRequestExcaption(){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request Data");
		}
}

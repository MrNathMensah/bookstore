package com.book.store.bookstore.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiError {
	
	private String message;
	private List<String> details;
	private HttpStatus status;
	private LocalDateTime time;
	
	
}

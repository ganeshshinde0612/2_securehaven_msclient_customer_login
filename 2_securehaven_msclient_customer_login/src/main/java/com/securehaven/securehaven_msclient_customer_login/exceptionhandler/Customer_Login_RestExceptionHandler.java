package com.securehaven.securehaven_msclient_customer_login.exceptionhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.securehaven.securehaven_msclient_customer_login.exceptions.Data_Not_Found_Exception;
import com.securehaven.securehaven_msclient_customer_login.exceptions.Data_Not_Saved_Exception;
import com.securehaven.securehaven_msclient_customer_login.exceptions.Invalid_Login;
import com.securehaven.securehaven_msclient_customer_login.model.Customer_login_Exception;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class Customer_Login_RestExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> notValidException(MethodArgumentNotValidException e)
	{
		List<ObjectError> l=e.getBindingResult().getAllErrors();
		
		Map<String,String> errors=new HashMap<String, String>();
		
		for(ObjectError a:l)
		{
			FieldError fe=(FieldError)a;
			errors.put(fe.getField(), fe.getDefaultMessage());
		}
		
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Data_Not_Found_Exception.class)
	public ResponseEntity<Customer_login_Exception> dataNotFound(Data_Not_Found_Exception e, HttpServletRequest uri)
	{
		Customer_login_Exception exceptions =new Customer_login_Exception();
		exceptions.setMsg(e.getMessage());
		exceptions.setDate(new Date());
		exceptions.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exceptions.setStatusMsg(HttpStatus.INTERNAL_SERVER_ERROR);
		exceptions.setUri(uri.getRequestURI());
		
		return new ResponseEntity<Customer_login_Exception>(exceptions, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Data_Not_Saved_Exception.class)
	public ResponseEntity<Customer_login_Exception> dataNotSaved(Data_Not_Saved_Exception e, HttpServletRequest uri)
	{
		Customer_login_Exception exceptions =new Customer_login_Exception();
		exceptions.setMsg(e.getMessage());
		exceptions.setDate(new Date());
		exceptions.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exceptions.setStatusMsg(HttpStatus.INTERNAL_SERVER_ERROR);
		exceptions.setUri(uri.getRequestURI());
		
		return new ResponseEntity<Customer_login_Exception>(exceptions, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Invalid_Login.class)
	public ResponseEntity<Customer_login_Exception> invalid_Login(Invalid_Login e, HttpServletRequest uri)
	{
		Customer_login_Exception exceptions=new Customer_login_Exception();
		exceptions.setDate(new Date());
		exceptions.setMsg(e.getMessage());
		exceptions.setStatusCode(HttpStatus.NOT_FOUND.value());
		exceptions.setStatusMsg(HttpStatus.NOT_FOUND);
		exceptions.setUri(uri.getRequestURI());
		
		return new ResponseEntity<Customer_login_Exception>(exceptions, HttpStatus.NOT_FOUND);	
	}
}

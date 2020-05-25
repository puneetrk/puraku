package com.puraku.microservices.currencyconversionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CurrencyConversionException extends RuntimeException{

	CurrencyConversionException(String message)
	{
		super(message);
	}
}

package com.puraku.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	Environment env;
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String,String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		ResponseEntity<CurrencyConversionBean> response = new RestTemplate().
					getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
							CurrencyConversionBean.class, uriVariables );
		CurrencyConversionBean body = response.getBody();
		
		return new CurrencyConversionBean(body.getId(),from, to, body.getConversionMultiple(), quantity,8100);
	}
	
	@GetMapping("currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean body = proxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversionBean(body.getId(),from, to, body.getConversionMultiple(), quantity,body.getPort());
	}
}

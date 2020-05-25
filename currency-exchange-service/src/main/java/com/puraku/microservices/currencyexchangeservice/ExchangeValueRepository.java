package com.puraku.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{

	// query method
	
	public ExchangeValue findByFromAndTo(String from,String to);
}

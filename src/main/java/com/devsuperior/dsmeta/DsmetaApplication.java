package com.devsuperior.dsmeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.dsmeta.repositories.SaleRepository;

@SpringBootApplication
public class DsmetaApplication{
	 
	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

 
}

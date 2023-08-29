package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String seller;
	
	
	public SaleDTO(Long id, Double amount, LocalDate date, String seller) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.seller = seller;
	}
	
	public SaleDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
		seller = entity.getSeller().getName(); 
		}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSeller() {
		return seller;
	}
	
}

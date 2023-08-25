package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerMinDTO {
	private String name;
	private Double amount;
	
	public SellerMinDTO() {
	}

	public SellerMinDTO(String name , Double amount) {
		this.name = name;
		this.amount= amount;
	}

	public SellerMinDTO(Seller entity) {
		name= entity.getName();
		amount = ((SaleMinDTO) entity.getSales()).getAmount();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	}


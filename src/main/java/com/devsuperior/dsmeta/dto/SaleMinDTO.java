package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.projections.SaleMinProjections;

public class SaleMinDTO {
	private String name;
	private Double amount;

	public SaleMinDTO(SaleMinProjections projection) {
		this.name = projection.getName();
		this.amount = projection.getAmount();
	}

	public SaleMinDTO(String name, Double amount, LocalDate minDate, LocalDate maxDate) {
		this.name = name;
		this.amount = amount;

	}

	public SaleMinDTO(SaleDTO projection) {
		name = projection.getSeller();
		amount = projection.getAmount();
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

	@Override
	public String toString() {
		return "SaleMinDTO [name=" + name + ", amount=" + amount + "]";
	}

}
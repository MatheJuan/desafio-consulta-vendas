package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<SaleMinDTO> reportSale(String min, String max, String name) {
		LocalDate minDate = LocalDate.parse(min);
		LocalDate maxDate = LocalDate.parse(max);
		if (maxDate == null) {
			maxDate= LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()); 
		}
		if (minDate == null) {
			minDate = maxDate.minusYears(1L);
		}
		List<Sale> sale = repository.searchReport(minDate, maxDate, name);
		return sale.stream().map(x -> new SaleMinDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public List<SellerMinDTO> summary(String min, String max){
		LocalDate minDate = LocalDate.parse(min);
		LocalDate maxDate = LocalDate.parse(max);
		if (maxDate == null) {
			maxDate= LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()); 
		}
		if (minDate == null) {
			minDate = maxDate.minusYears(1L);
		}
		List<Seller> seller = repository.searchSummary(minDate, maxDate);
		return seller.stream().map(x -> new SellerMinDTO(x)).toList();
	}
}

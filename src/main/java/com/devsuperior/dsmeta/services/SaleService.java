package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjections;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<SaleDTO> reportSale(String min, String max, String name, Pageable pageable) {
		LocalDate minDate=null;
		LocalDate maxDate=null;

		if (min == null || min.equals("0")) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			minDate = maxDate.minusYears(1L);
			
		} else if (max == null || max.equals("0") ) {
			minDate = LocalDate.parse(min);
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			
		} else {
			minDate = LocalDate.parse(min);
			maxDate = LocalDate.parse(max);
		}
		Page<Sale> sale = repository.searchReport(minDate, maxDate, name, pageable);
		return sale.map(x -> new SaleDTO(x));
	}

	@Transactional(readOnly = true)
	public List<SaleMinDTO> summary(String min, String max) {
		LocalDate minDate=null;
		LocalDate maxDate=null;

		if (min == null || min.equals("0")) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			minDate = maxDate.minusYears(1L);
			
		} else if (max == null || max.equals("0") ) {
			minDate = LocalDate.parse(min);
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			
		} else {
			minDate = LocalDate.parse(min);
			maxDate = LocalDate.parse(max);
		}
		
		List<SaleMinProjections> result = repository.searchSummary(minDate, maxDate);
		return result.stream().map(x -> new SaleMinDTO(x)).collect(Collectors.toList());

	}
}

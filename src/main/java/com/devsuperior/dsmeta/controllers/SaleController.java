package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
		SaleDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleDTO>> getReport(@RequestParam(name = "minDate", required=false) String minDate, 
			@RequestParam(name = "maxDate", required=false)String maxDate, @RequestParam(name = "name", required=false)
	String name, Pageable pageable ) {
		Page<SaleDTO> dto = service.reportSale(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleMinDTO>> getSummary(@RequestParam(name="minDate", required = false )String minDate,
			@RequestParam(name="maxDate", required = false)String maxDate){
			List<SaleMinDTO> dto = service.summary(minDate, maxDate);
			return ResponseEntity.ok(dto);
	}
}
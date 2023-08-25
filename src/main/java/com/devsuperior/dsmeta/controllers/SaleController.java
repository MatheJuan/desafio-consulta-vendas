package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<List<SaleMinDTO>> getReport(@RequestParam(name = "minDate") String minDate, 
			@RequestParam(name = "maxDate")String maxDate, @RequestParam(name = "name", defaultValue = "")
	String name ) {
		List<SaleMinDTO> dto = service.reportSale(minDate, maxDate, name);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SellerMinDTO>> getSummary(@RequestParam(name="minDate")String minDate,
			@RequestParam(name="maxDate")String maxDate){
			List<SellerMinDTO> dto = service.summary(minDate, maxDate);
			return ResponseEntity.ok(dto);
	}
}
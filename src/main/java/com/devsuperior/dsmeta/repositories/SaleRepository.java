package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjections;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT DISTINCT obj FROM Sale obj JOIN obj.seller seller "
			+ " WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name ,'%'))"
			+ " AND obj.date>=:minDate AND obj.date<= :maxDate ORDER BY obj.date DESC")
	Page<Sale> searchReport(LocalDate minDate, LocalDate maxDate,String name, Pageable pageable);

	@Query(nativeQuery = true,value="SELECT public.tb_seller.name, SUM(tb_sales.amount) as Amount FROM public.tb_sales "
								+ "INNER JOIN public.tb_seller ON tb_sales.seller_id = tb_seller.id WHERE tb_sales.date>= :minDate  AND tb_sales.date<=:maxDate "
								+ "GROUP BY tb_seller.name ;")
	List<SaleMinProjections> searchSummary(LocalDate minDate, LocalDate maxDate);
	
}
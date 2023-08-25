package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT DISTINCT obj FROM Sale obj JOIN obj.seller seller "
			+ " WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name ,'%'))"
			+ " AND obj.date>=:minDate AND obj.date<= :maxDate ORDER BY obj.date ASC")
	List<Sale> searchReport(LocalDate minDate, LocalDate maxDate,String name);

	@Query(nativeQuery = true,value="SELECT tb_seller.name, SUM(tb_sales.amount) FROM public.tb_sales "
								+ "INNER JOIN public.tb_seller ON tb_sales.seller_id = tb_seller.id WHERE tb_sales.date>= ?1  AND tb_sales.date<=?2 GROUP BY tb_seller.name")
	List<Seller> searchSummary(LocalDate minDate, LocalDate maxDate);
	
}

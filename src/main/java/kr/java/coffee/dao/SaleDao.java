package kr.java.coffee.dao;

import java.util.List;
import java.util.Map;

import kr.java.coffee.dto.Sale;

public interface SaleDao {
	Sale selectSaleByNo(Sale sale);

	List<Sale> selectSaleByAll();
	List<Sale> callSaleDetail(Map<String, Boolean> map);
	
	int insertSale(Sale sale);

	int deleteSale(Sale sale);
	
	int updateSale(Sale sale);
	
	List<Map<String, Object>> getTotal();
}

package kr.java.coffee.dao;

import java.util.List;

import kr.java.coffee.dto.Product;

public interface ProductDao {
	Product selectProductByNo(Product product);

	List<Product> selectProductByAll();

	int insertProduct(Product product);

	int deleteProduct(Product product);
	
	int updateProduct(Product product);
}

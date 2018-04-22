package kr.java.coffee.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.java.coffee.dto.Product;

public class ProductService extends DaoService {
    private static final ProductService instance = new ProductService();
    private String namespace = "kr.java.coffee.dao.ProductDao";
    
    private ProductService() {}

	public static ProductService getInstance() {
		return instance;
	}

	public List<Product> selectProductAll(){
		return processQueryList((SqlSession sqlSession)->sqlSession.selectList(namespace + ".selectProductAll"), "selectProductAll");
	}
	
	public Product selectProductByNo(Product product) {
		return processQueryItem((SqlSession sqlSession)->sqlSession.selectOne(namespace + ".selectProductByNo", product), "selectProductByNo");
	}
	
	public int insertProduct(Product product) {
		return processQueryUpdate((SqlSession sqlSession)-> sqlSession.insert(namespace + ".insertProduct", product), "insertProduct");
	}
	
	public int deleteProduct(Product product) {
		return processQueryUpdate((SqlSession sqlSession)-> sqlSession.delete(namespace + ".deleteProduct", product), "deleteProduct");
	}
	
	public int updateProduct(Product product) {
		return processQueryUpdate((SqlSession sqlSession)-> sqlSession.update(namespace + ".updateProduct", product), "updateProduct");
	}
}

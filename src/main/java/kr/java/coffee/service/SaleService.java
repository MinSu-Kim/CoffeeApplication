package kr.java.coffee.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.java.coffee.dto.Sale;

public class SaleService extends DaoService {
	private static final SaleService instance = new SaleService();
	private String namespace = "kr.java.coffee.dao.SaleDao";
	
	private SaleService() {}

	public static SaleService getInstance() {
		return instance;
	}

	public List<Sale> selectSaleByAll() {
		return processQueryList((SqlSession sqlSession)->sqlSession.selectList(namespace + ".selectSaleByAll"), "selectSaleByAll");
	}

	public Sale selectSaleByNo(Sale sale) {
		return processQueryItem((SqlSession sqlSession)->sqlSession.selectOne(namespace + ".selectSaleByNo", sale), "selectSaleByNo");
	}

	public int insertSale(Sale sale) {
		return processQueryUpdate((SqlSession sqlSession)->sqlSession.insert(namespace+".insertSale", sale), "insertProduct");
	}

	public int updateSale(Sale sale) {
		return processQueryUpdate((SqlSession sqlSession)->sqlSession.update(namespace+".updateSale", sale), "updateSale");
	}

	public int deleteSale(Sale sale) {
		return processQueryUpdate((SqlSession sqlSession)->sqlSession.delete(namespace+".deleteSale", sale), "deleteSale");
	}

	public List<Sale> callSaleDetail(Map<String, Boolean> map) {
		return processQueryList((SqlSession sqlSession)->sqlSession.selectList(namespace + ".callSaleDetail", map), "callSaleDetail");
	}

	public List<Map<String, Object>> getTotal() {
		return processQueryList((SqlSession sqlSession)->sqlSession.selectList(namespace + ".getTotal"), "getTotal");	
	}
}

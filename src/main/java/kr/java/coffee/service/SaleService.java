package kr.java.coffee.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.java.coffee.dto.Sale;
import kr.java.coffee.util.MyBatisSqlSessionFactory;

public class SaleService {
	private static final Log log = LogFactory.getLog(SaleService.class);
	private static final SaleService instance = new SaleService();
	private SqlSessionFactory sessionFactory;
	
	private String namespace = "kr.java.coffee.dao.SaleDao";
	
	private SaleService() {
		sessionFactory = MyBatisSqlSessionFactory.getSqlSessionFactory();
	}

	public static SaleService getInstance() {
		return instance;
	}

	public List<Sale> selectSaleByAll(){
		log.debug("selectSaleByAll()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			return sqlSession.selectList(namespace + ".selectSaleByAll");
		}
	}
	
	public Sale selectSaleByNo(Sale sale) {
		log.debug("selectSaleByNo()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			return sqlSession.selectOne(namespace + ".selectSaleByNo", sale);
		}
	}
	
	public int insertSale(Sale sale) {
		log.debug("insertSale()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			int res = sqlSession.insert(namespace+".insertSale", sale);
			sqlSession.commit();
			return res;
		}
	}
	
	public int updateSale(Sale sale) {
		try(SqlSession sqlSession = sessionFactory.openSession()){
			int res = sqlSession.update(namespace+".updateSale", sale);
			sqlSession.commit();
			return res;
		}
	}
	
	public int deleteSale(Sale sale) {
		log.debug("deleteSale()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			int res = sqlSession.delete(namespace+".deleteSale", sale);
			sqlSession.commit();
			return res;
		}
	}
	
	public List<Sale> callSaleDetail(Map<String, Boolean> map){
		log.debug("callSaleDetail()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			return sqlSession.selectList(namespace + ".callSaleDetail", map);
		}
	}

	public List<Map<String, Object>> getTotal(){
		log.debug("getTotal()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			return sqlSession.selectList(namespace + ".getTotal");
		}
	}
}

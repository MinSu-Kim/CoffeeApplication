package kr.java.coffee.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.java.coffee.util.MyBatisSqlSessionFactory;

public class SqlSessionService {
	private static final Logger log = LogManager.getLogger();
	
	protected SqlSessionFactory sessionFactory;

	public SqlSessionService() {
		sessionFactory = MyBatisSqlSessionFactory.getSqlSessionFactory();
	}

	protected <T> List<T> processQueryList(SqlSessionExecuteList<T> p, String logMsg) {
		log.debug(logMsg + "()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			return p.executeQuery(sqlSession);
		}
	}

	protected <T> T processQueryItem(SqlSessionExecuteItem<T> p, String logMsg) {
		log.debug(logMsg + "()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			return p.exectueQuery(sqlSession);
		}
	}

	protected int processQueryUpdate(SqlSessionExecuteUpdate p, String logMsg) {
		log.debug(logMsg + "()");
		try(SqlSession sqlSession = sessionFactory.openSession()){
			int res = p.executeQuery(sqlSession);
			sqlSession.commit();
			return res;
		}
	}
	
	interface SqlSessionExecuteList<T>{
		List<T> executeQuery(SqlSession sqlSession);
	}

	interface SqlSessionExecuteItem<T>{
		T exectueQuery(SqlSession sqlSession);
	}

	interface SqlSessionExecuteUpdate{
		int executeQuery(SqlSession sqlSession) ;
	}

}
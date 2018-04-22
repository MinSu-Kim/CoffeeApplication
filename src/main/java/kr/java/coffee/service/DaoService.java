package kr.java.coffee.service;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.java.coffee.util.MyBatisSqlSessionFactory;

public class DaoService {
	private static final Log log = LogFactory.getLog(DaoService.class);
	protected SqlSessionFactory sessionFactory;

	public DaoService() {
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

	interface SqlSessionExecuteItem<T> {
		T exectueQuery(SqlSession sqlSession);
	}
	interface SqlSessionExecuteList<T> {
		List<T> executeQuery(SqlSession sqlSession);
	}
	interface SqlSessionExecuteUpdate {
		int executeQuery(SqlSession sqlSession) ;
	}
}
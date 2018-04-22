package kr.java.coffee.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlSessionFactory {
	private static final Log log = LogFactory.getLog(MyBatisSqlSessionFactory.class);
	
	private static SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory getSqlSessionFactory() {
		
		if (sqlSessionFactory == null) {
			try (InputStream inputSream = Resources.getResourceAsStream("mybatis-config.xml");) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputSream);
				log.debug("getSqlSessionFactory() - Connection : " + sqlSessionFactory.openSession().getConnection());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}

}

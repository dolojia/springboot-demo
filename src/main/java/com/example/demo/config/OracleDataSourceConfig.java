package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/** 
 * @Describe:Oracle数据源<br>
 * @author: 100196 <br>
 * @Createdate：2017年12月6日下午4:58:46 <br>
 * @E-mail:  dengjiaxing@dafycredit.com <br> 
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.oracle.mapper", sqlSessionTemplateRef = "oracleSqlSessionTemplate")
public class OracleDataSourceConfig {
	
	@Bean(name = "oracleDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.mysql2")
	@Primary
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "oracleSqlSessionFactory")
	@Primary
	public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/oracle/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "oracleTransactionManager")
	@Primary
	public DataSourceTransactionManager oracleTransactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "oracleSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate oracleSqlSessionTemplate(
			@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

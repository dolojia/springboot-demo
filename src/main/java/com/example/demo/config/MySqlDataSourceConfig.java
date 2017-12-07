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
 * @Describe:mysql数据源<br>
 * @author: 100196 <br>
 * @Createdate：2017年12月6日下午4:58:28 <br>
 * @E-mail:  dengjiaxing@dafycredit.com <br> 
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mysql.mapper", sqlSessionTemplateRef = "mySqlSessionTemplate")
public class MySqlDataSourceConfig {
	
	@Bean(name = "mySqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	@Primary
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mySqlSessionFactory")
	@Primary
	public SqlSessionFactory mySqlSessionFactory(@Qualifier("mySqlDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/mysql/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "mySqlTransactionManager")
	@Primary
	public DataSourceTransactionManager mySqlTransactionManager(@Qualifier("mySqlDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "mySqlSessionTemplate")
	@Primary
	public SqlSessionTemplate mySqlSessionTemplate(
			@Qualifier("mySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

package com.bm.jw.conf.database;

import javax.sql.DataSource;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.ibeetl.starter.BeetlSqlCustomize;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	
	@Bean(name = "dataSource")
	public DataSource datasource(Environment env) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		return ds;
	}


	@Bean
	public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("dataSource") DataSource dataSource){
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(dataSource);
		return source;
	}

	@Bean
	public BeetlSqlCustomize beetlSqlCustomize() {
		return new BeetlSqlCustomize() {
			@Override
			public void customize(SqlManagerFactoryBean sqlManager) {
				sqlManager.setDbStyle(new MySqlStyle());

			}

		};
	}


////配置包扫描
//@Bean(name = "beetlSqlScannerConfigurer")
//public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
//	BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
//	conf.setBasePackage(" com.bm.jw.dao");
//	conf.setDaoSuffix("Dao");
//	conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
//	return conf;
//}
//
//	@Bean(name = "sqlManagerFactoryBean")
//	@Primary
//	public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("dataSource") DataSource datasource) {
//		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
//		BeetlSqlDataSource source = new BeetlSqlDataSource();
//		source.setMasterSource(datasource);
//		factory.setCs(source);
//		factory.setDbStyle(new MySqlStyle());
//		factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
//		factory.setNc(new UnderlinedNameConversion());
//		factory.setSqlLoader(new ClasspathLoader("/sql"));
//		//sql文件路径
//		return factory;
//	}
}
package com.wade.demo.config;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("nutz")
public class NutzConfig {

	private String[] paths;

	@Autowired
	private DataSource dataSource;

	@Bean
	@ConditionalOnMissingBean
	public SqlManager sqlManager() {
		String[] pathsArr = this.paths;
		if (pathsArr == null) {
			pathsArr = new String[] { "sqls" };
		}
		return new FileSqlManager(pathsArr);
	}

	@Bean
	public Dao dao() {
		NutDao nutDao = new NutDao();
		nutDao.setDataSource(dataSource);
		nutDao.setSqlManager(sqlManager());
		return nutDao;
	}

	public Sql getQueryRecordSqlByKey(String key) {
		return Sqls.queryRecord(dao().sqls().get(key));
	}
}

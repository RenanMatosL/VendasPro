package br.com.empresa.vendapro.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"br.com.empresa.vendapro.dao" })
@EntityScan(basePackages = { "br.com.empresa.vendapro.model" })
@PropertySource(value = "application.properties")
public class DataSourcePadrao {
	@Value("${datasource.host}")
	private String hostBase;

	@Value("${datasource.usuario}")
	private String usuarioBase;

	@Value("${datasource.senha}")
	private String senhaBase;

	@Value("${datasource.hibernate.dialect}")
	private String dialetoBase;

	@Value("${datasource.driverClassName}")
	private String driverBase;

	@Value("${datasource.hibernate.showSql}")
	private String showSql;

	@Bean(name = "jpaVendorAdapter")
	public JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}

	private DataSource getDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driverBase);
		basicDataSource.setUrl(hostBase);
		basicDataSource.setUsername(usuarioBase);
		basicDataSource.setPassword(senhaBase);

		return basicDataSource;
	}

	@Bean(name = "entityManager")
	public EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}

	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory getEntityManagerFactory() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", dialetoBase);
		properties.put("hibernate.show_sql", showSql != null && showSql.equals(true) ? showSql : false);
		properties.setProperty("hibernate.hbm2ddl.auto", "update");

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		localContainerEntityManagerFactoryBean.setDataSource(getDataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(getJpaVendorAdapter());

		localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] { "br.com.empresa.vendapro.model" });

		localContainerEntityManagerFactoryBean.setPersistenceUnitName("persistenceUnit");

		localContainerEntityManagerFactoryBean.setJpaProperties(properties);

		localContainerEntityManagerFactoryBean.afterPropertiesSet();

		return localContainerEntityManagerFactoryBean.getObject();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(getEntityManagerFactory());
	}
}
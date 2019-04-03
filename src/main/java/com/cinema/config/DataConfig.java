package com.cinema.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.ResourceBundle;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.cinema.model.dao")
public class DataConfig {

    private static final String PROP_DB_DRIVER = "db.driver";
    private static final String PROP_DB_URL = "db.url";
    private static final String PROP_DB_USER = "db.user";
    private static final String PROP_DB_PASS = "db.pass";

    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_ID_GENERATOR = "hibernate.id.new_generator_mappings";
    private static final String PROP_HIBERNATE_HBM2DDL_IMPORT = "hibernate.hbm2ddl.import_files";
    private static final String PROP_ENT_MAN_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    private ResourceBundle resource = ResourceBundle.getBundle("app");

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(resource.getString(PROP_DB_DRIVER));
        dataSource.setUrl(resource.getString(PROP_DB_URL));
        dataSource.setUsername(resource.getString(PROP_DB_USER));
        dataSource.setPassword(resource.getString(PROP_DB_PASS));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(resource.getString(PROP_ENT_MAN_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return entityManagerFactoryBean;
    }
    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(PROP_HIBERNATE_DIALECT, resource.getString(PROP_HIBERNATE_DIALECT));
        hibernateProperties.setProperty(PROP_HIBERNATE_HBM2DDL, resource.getString(PROP_HIBERNATE_HBM2DDL));
        hibernateProperties.setProperty(PROP_HIBERNATE_ID_GENERATOR, resource.getString(PROP_HIBERNATE_ID_GENERATOR));
        hibernateProperties.setProperty(PROP_HIBERNATE_SHOW_SQL, resource.getString(PROP_HIBERNATE_SHOW_SQL));
        hibernateProperties.setProperty(PROP_HIBERNATE_HBM2DDL_IMPORT, resource.getString(PROP_HIBERNATE_HBM2DDL_IMPORT));
        return hibernateProperties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}



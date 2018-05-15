/*
 *
 */
package net.vivekkumar.spring.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The Class AppConfig.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.vivekkumar.spring")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("net.vivekkumar.spring.repositories")
public class AppConfig {

    /** The Constant PROPERTY_NAME_DATABASE_DRIVER. */
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

    /** The Constant PROPERTY_NAME_DATABASE_PASSWORD. */
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";

    /** The Constant PROPERTY_NAME_DATABASE_URL. */
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";

    /** The Constant PROPERTY_NAME_DATABASE_USERNAME. */
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    /** The Constant PROPERTY_NAME_HIBERNATE_DIALECT. */
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

    /** The Constant PROPERTY_NAME_HIBERNATE_SHOW_SQL. */
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    /** The Constant PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN. */
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    /** The env. */
    @Resource
    private Environment env;

    /**
     * Data source.
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    /**
     * Entity manager factory.
     *
     * @return the local container entity manager factory bean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean
                .setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    /**
     * Hib properties.
     *
     * @return the properties
     */
    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        return properties;
    }

    /**
     * Transaction manager.
     *
     * @return the jpa transaction manager
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
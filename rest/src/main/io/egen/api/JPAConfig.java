package io.egen.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:application.properties")
public class JPAConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean createEMFSingleton() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setDataSource(getDs());
        emf.setPackagesToScan("io.egen.api");
        emf.setJpaProperties(getProperties());

        return emf;
    }


    @Bean
    public PlatformTransactionManager tnxMgr(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


    @Bean
    public DriverManagerDataSource getDs() {
        DriverManagerDataSource dmds = new DriverManagerDataSource();

        dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dmds.setUrl(env.getProperty("db.url"));
        dmds.setUsername(env.getProperty("db.user", "root"));
        dmds.setPassword(env.getProperty("db.password", "password"));

        return dmds;
    }


    private Properties getProperties() {
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));

        return props;
    }
}

package com.capestart.api.employee.config;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.capestart.api.employee"),
        @ComponentScan("com.capestart.api.employee") })
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        System.out.println("Starting of DB Connection");
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties props = new Properties();
        // Setting JDBC properties
        props.put(DRIVER, env.getProperty("mysql.driver"));
        props.put(URL, env.getProperty("mysql.url"));
        props.put(USER, env.getProperty("mysql.user"));
        props.put(PASS, env.getProperty("mysql.password"));

        // Setting Hibernate properties
        props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));



        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("com.capestart.api.employee.model");
        System.out.println("End of Db Connection");
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        System.out.println("Inside Hibernate Transaction Manager");
        return transactionManager;
    }
}

package com.mtl.config;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.NamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SessionFactoryConfig {
    private Logger LOGGER = Logger.getLogger(SessionFactoryConfig.class);
    @Autowired
    private DataSource dataSource;

//    在Spring-ORM4 整合hibernate4中不用配置lobHandler
//    @Autowired
//    private LobHandler lobHandler;

    @Value("${spring.jpa.packagestoscan}")
    private String packagestoscan;

    @Value("${spring.jpa.database-platform}")
    private String databaseplatform;

    @Value("${spring.jpa.show-sql}")
    private boolean showsql;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlauto;

    @Value("${spring.jpa.hibernate.naming.strategy}")
    private String namingstrategy;

    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(packagestoscan);
        Properties props = new Properties();
        props.put("hibernate.dialect",databaseplatform);
        props.put("hibernate.show_sql",showsql);
        props.put("hibernate.ddl-auto",ddlauto);
        sessionFactory.setHibernateProperties(props);
        try {
            NamingStrategy strategy = (NamingStrategy) Class.forName(namingstrategy).newInstance();
            sessionFactory.setNamingStrategy(strategy);
        } catch (InstantiationException e) {
            LOGGER.error("strategy InstantiationException!",e);
        } catch (IllegalAccessException e) {
            LOGGER.error("strategy IllegalAccessException!",e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("strategy ClassNotFoundException!",e);
        }

        try {
            sessionFactory.afterPropertiesSet();
        } catch (Exception e) {
            LOGGER.error("execute sessionFactory.afterPropertiesSet failed!",e);
        }finally {
            return  sessionFactory.getObject();
        }
    }
}

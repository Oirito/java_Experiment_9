package com.example.partc;

import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.Properties;
import org.hibernate.SessionFactory;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/bankdb");
        ds.setUsername("root");
        ds.setPassword(""); // change if needed
        return ds;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("com.example.partc");
        Properties props = new Properties();
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("show_sql","true");
        sf.setHibernateProperties(props);
        return sf;
    }
    @Bean
    public HibernateTransactionManager txManager(SessionFactory sf) {
        return new HibernateTransactionManager(sf);
    }
    @Bean
    public BankService bankService(SessionFactory sf) {
        return new BankService(sf);
    }
}

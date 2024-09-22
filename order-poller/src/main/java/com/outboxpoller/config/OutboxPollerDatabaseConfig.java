//package com.outboxpoller.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = "com.outbox.order.repository",
//        entityManagerFactoryRef = "outboxPollerEmFactory",
//        transactionManagerRef = "outboxPollerTransactionManager"
//)
//public class OutboxPollerDatabaseConfig {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.order")
//    public DataSourceProperties orderDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "outboxPollerDataSource")
//    @Primary
//    public DataSource outboxPollerDataSource() {
//
////        BasicDataSource writeDataSource = new BasicDataSource();
////        writeDataSource.setUrl(url);
////        writeDataSource.setUsername(username);
////        writeDataSource.setPassword(password);
////        return writeDataSource;
//        return orderDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//
//
//    @Bean(name = "outboxPollerEmFactory")
//    @Primary
//    public EntityManagerFactory outboxPollerEmFactory(@Qualifier("outboxPollerDataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.outbox.order");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        em.setJpaPropertyMap(additionalProperties());
//        em.afterPropertiesSet();
//        return em.getObject();
//    }
//
//    Map<String, Object> additionalProperties() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
////        properties.put("spring.jpa.hibernate.ddl-auto", "update");
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        return properties;
//    }
//
//    @Bean(name = "outboxPollerTransactionManager")
//    @Primary
//    public JpaTransactionManager outboxPollerTransactionManager(@Qualifier("outboxPollerEmFactory") EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
//        return jpaTransactionManager;
//    }
//}

//package com.outboxpoller.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
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
//@EnableJpaRepositories(basePackages = "com.outbox.repository",
//        entityManagerFactoryRef = "outboxOrderEmFactory",
//        transactionManagerRef = "outboxOrderTransactionManager")
//public class OutboxOrderDatabaseConfig {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.outbox-order")
//    public DataSourceProperties outboxOrderDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "outboxOrderDataSource")
//    public DataSource outboxOrderDataSource() {
//
////        BasicDataSource writeDataSource = new BasicDataSource();
////        writeDataSource.setUrl(url);
////        writeDataSource.setUsername(username);
////        writeDataSource.setPassword(password);
////        return writeDataSource;
//        return outboxOrderDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//
//
//    @Bean(name = "outboxOrderEmFactory")
//    public EntityManagerFactory outboxOrderEmFactory(@Qualifier("outboxOrderDataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.outbox.entity");
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
//    @Bean(name = "outboxOrderTransactionManager")
//    public JpaTransactionManager outboxOrderTransactionManager(@Qualifier("outboxOrderEmFactory") EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
//        return jpaTransactionManager;
//    }
//}

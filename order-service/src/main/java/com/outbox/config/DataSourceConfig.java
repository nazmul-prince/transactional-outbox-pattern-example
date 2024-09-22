//package com.outbox.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.demo.dbreadwrite.config.DataSourceType.MASTER;
//import static com.demo.dbreadwrite.config.DataSourceType.READ_REPLICA;
//
//enum DataSourceType {
//    READ_REPLICA, MASTER;
//}
//
//@Configuration
//@EnableJpaRepositories(basePackages = {"com.demo.dbreadwrite.dao"})
//@EnableTransactionManagement
//public class DataSourceConfig {
//
////    @Bean
////    @ConfigurationProperties("spring.datasource.order")
////    public DataSourceProperties orderDataSourceProperties() {
////        return new DataSourceProperties();
////    }
////
////    @Bean
////    @ConfigurationProperties("spring.datasource.outboxOrder")
////    public DataSourceProperties outboxOrderDataSourceProperties() {
////        return new DataSourceProperties();
////    }
//
////    @Bean(name = "orderDataSource")
////    public DataSource orderDataSource(@Value("${read.datasource.url}") String url,
////                                     @Value("${read.datasource.username}") String username,
////                                     @Value("${read.datasource.password}") String password) {
//////        BasicDataSource readDataSource = new BasicDataSource();
//////        readDataSource.setUrl(url);
//////        readDataSource.setUsername(username);
//////        readDataSource.setPassword(password);
//////        return readDataSource;
////        return orderDataSourceProperties()
////                .initializeDataSourceBuilder()
////                .build();
////    }
////
////    @Bean(name = "outboxOrderDataSource")
////    public DataSource writeDataSource(@Value("${write.datasource.url}") String url,
////                                      @Value("${write.datasource.username}") String username,
////                                      @Value("${write.datasource.password}") String password) {
////
//////        BasicDataSource writeDataSource = new BasicDataSource();
//////        writeDataSource.setUrl(url);
//////        writeDataSource.setUsername(username);
//////        writeDataSource.setPassword(password);
//////        return writeDataSource;
////        return outboxOrderDataSourceProperties()
////                .initializeDataSourceBuilder()
////                .build();
////    }
//
////    @Bean(name = "routingDataSource")
////    @DependsOn({"writeDataSource", "readDataSource"})
////    public DataSource routingDataSource(@Qualifier("writeDataSource") DataSource writeDataSource,
////                                        @Qualifier("readDataSource") DataSource readDataSource) {
////        final Map<Object, Object> dataSourceMap = new HashMap<>();
////        dataSourceMap.put(MASTER, writeDataSource);
////        dataSourceMap.put(READ_REPLICA, readDataSource);
////
////        final AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
////
////            @Override
////            protected Object determineCurrentLookupKey() {
////                return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? READ_REPLICA : MASTER;
////            }
////        };
////
////        routingDataSource.setTargetDataSources(dataSourceMap);
////        routingDataSource.setDefaultTargetDataSource(writeDataSource);
////        return routingDataSource;
////    }
//
////    @Bean(name = "dataSource")
////    @DependsOn("routingDataSource")
////    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
////        return new LazyConnectionDataSourceProxy(routingDataSource);
////    }
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.demo.dbreadwrite.model");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        em.setJpaPropertyMap(additionalProperties());
//        em.afterPropertiesSet();
//        return em.getObject();
//    }
//
//    Map<String, Object> additionalProperties() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        return properties;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
//        return jpaTransactionManager;
//    }
//
//
//}

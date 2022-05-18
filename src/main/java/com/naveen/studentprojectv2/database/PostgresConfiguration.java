package com.naveen.studentprojectv2.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEMF",
        transactionManagerRef = "postgresTM",
        basePackages = {
                "com.naveen.studentprojectv2.student"
        }
)
public class PostgresConfiguration {

    @Primary
    @Bean(name="postgresDS")
    @ConfigurationProperties(prefix="postgres.datasource")
    public DataSource getDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name="postgresEMF")
    public LocalContainerEntityManagerFactoryBean entityManager(EntityManagerFactoryBuilder emfb){
        HashMap<String,Object> additionalProperties=new HashMap<>();
        additionalProperties.put("hibernate.hbm2ddl.auto","create-drop");
        additionalProperties.put("hibernate.show_sql","true");
        additionalProperties.put("hibernate.format_sql","true");

        return emfb
                .dataSource(getDataSource())
                .packages("com.naveen.studentprojectv2.student")
                .persistenceUnit("postgres")
                .properties(additionalProperties)
                .build();

    }

    @Primary
    @Bean(name="postgresTM")
    public PlatformTransactionManager transactionManager(@Qualifier("postgresEMF") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


}
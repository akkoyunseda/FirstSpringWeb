package com.garanti.FirstSpringWeb.config;

import com.garanti.FirstSpringWeb.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Component
//bu da olabilir
//@EnableTransactionManagement
public class BeanFactory
{
    @Bean(value = "myds")
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource(Constants.URL, Constants.USER, Constants.PASSWORD);
        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        return ds;
    }

    @Bean(value = "txManager")
    @DependsOn(value = "myds")
    public DataSourceTransactionManager getTransactionManager(@Autowired @Qualifier(value = "myds") DataSource ds){
        return  new DataSourceTransactionManager(ds);
    }


    @Bean
    @DependsOn(value = "myds")
    public JdbcTemplate getJdbcTemplate(@Autowired @Qualifier(value = "myds") DataSource ds)
    {
        return new JdbcTemplate(ds);
    }

    @Bean
    @DependsOn(value = "myds")
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(@Autowired @Qualifier(value = "myds") DataSource ds){
        return new NamedParameterJdbcTemplate(ds);

    }
}
package com.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        initializeDatabase();
    }
    public void initializeDatabase(){
        try {
            Resource dataResource=new ClassPathResource("data.sql");
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), dataResource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

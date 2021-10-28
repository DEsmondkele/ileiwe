package com.ileiwe.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.relational.Database;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Data
@Slf4j
public class DataBaseTest {
    @Autowired
    DataSource dataSource;

    @Value("${test-name}")
    private String name;
    @Value("${test-year}")
    private String currentYear;

    @Test
    public void printValues(){
        assertThat(name).isEqualTo("Jack");
        assertThat(currentYear).isEqualTo("2020");
        log.info("Test name->{}", name);
        log.info("Test year->{}", currentYear);
    }

    @Test
    public void  connectToDatabaseTest(){

        try{
            Connection connection = dataSource.getConnection();
            assertThat(connection).isNotNull();
            log.info("Database --> {}",connection.getCatalog());
        } catch (SQLException exception) {
            log.info("An exception occurred --> {}", exception.getMessage());
        }
    }
}

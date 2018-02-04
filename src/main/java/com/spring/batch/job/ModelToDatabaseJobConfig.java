package com.spring.batch.job;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.spring.batch.dao.StudentPreparedStatementSetter;

import com.spring.batch.model.StudentDTO;

@Configuration
public class ModelToDatabaseJobConfig {
	
	private static final String QUERY_INSERT_STUDENT = "INSERT " +
            "INTO students(email_address, name, purchased_package) " +
            "VALUES (?, ?, ?)";
	
	/*@Autowired
	DataSource datasource;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;*/
	
	@Bean(name="studentItemWriter")
    ItemWriter<StudentDTO> csvFileDatabaseItemWriter(DataSource dataSource, 
                                                     NamedParameterJdbcTemplate jdbcTemplate) {
		JdbcBatchItemWriter<StudentDTO> databaseItemWriter = new JdbcBatchItemWriter<>();
        databaseItemWriter.setDataSource(dataSource);
        databaseItemWriter.setJdbcTemplate(jdbcTemplate);
        databaseItemWriter.setSql(QUERY_INSERT_STUDENT);
        ItemPreparedStatementSetter<StudentDTO> valueSetter = 
                new StudentPreparedStatementSetter();
        databaseItemWriter.setItemPreparedStatementSetter(valueSetter);
         
        return databaseItemWriter;
    }

}

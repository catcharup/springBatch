package com.spring.batch.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.spring.batch.model.StudentDTO;

public final class StudentPreparedStatementSetter implements ItemPreparedStatementSetter<StudentDTO> {
	 
    @Override
    public void setValues(StudentDTO student, 
                          PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, student.getEmailAddress());
        preparedStatement.setString(2, student.getName());
        preparedStatement.setString(3, student.getPurchasedPackage());
    }
}

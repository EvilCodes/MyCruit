package com.oucre.core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public interface UserBatchPreparedStatementSetter extends BatchPreparedStatementSetter{

	void setValues(PreparedStatement ps, int i) throws SQLException;
	
	int getBatchSize();
}

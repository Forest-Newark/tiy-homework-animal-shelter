package com.forestnewark;

import java.sql.*;

/**
 * Created by forestnewark on 4/3/17.
 */
public class AnimalRepository {
    private Connection conn;

    public AnimalRepository(String jdbcUrl) throws SQLException {
        this.conn = DriverManager.getConnection(jdbcUrl);
    }

    public ResultSet listAnimal() throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM ANIMAL");
    }

}

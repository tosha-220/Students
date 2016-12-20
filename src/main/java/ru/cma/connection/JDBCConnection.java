package ru.cma.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCConnection {
    private Statement stm;
    private String url = "jdbc:mysql://localhost:3306/school";
    private String user = "root";
    private String pas = "12345";

    public Statement getStatement() {
        try {
            Connection connection = DriverManager.getConnection(url, user, pas);
            stm = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stm;
    }
}

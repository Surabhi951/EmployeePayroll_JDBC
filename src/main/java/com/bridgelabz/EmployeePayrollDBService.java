package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeePayrollDBService {

    //creating getConnection method and throws sql Exception
    Connection getConnection() throws SQLException {
        Connection connection;
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service_new?characterEncoding=utf8";
        String userName = "root";
        String Password = "root123";
        //getConnection method of DriverManager class attempts to establish a connection to the database by using the given db url
        connection = DriverManager.getConnection(jdbcUrl, userName, Password);
        System.out.println("Connection is successful!!" + connection);
        return connection;
    }
}

package com.attech.estore.config;

import java.sql.*;

public class DBconnection {
    //Creating database Connection in java online shopping system
    public static Connection connection;

    // Creating universal method to open connect will mysql database
    public static Connection getConnection() {
        try {
            //Registering with mysql Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?useSSL=false", "root", "atumar4031");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (connection);
    }

    // Creating universal method to close connect will mysql database
    public static void CloseConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Creating universal method to query for retrieving information
    public static ResultSet getResultFromSqlQuery(String SqlQueryString, String email) {
        //Creating Resultset object
        ResultSet rs = null;
        try {
            Connection connection = getConnection();
            //Checking whether the connection is null or null
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(SqlQueryString);
                preparedStatement.setString(1, email);
                rs = preparedStatement.executeQuery();
            } //Querying the query rs = connection.creat
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    //Creating universal method to query for inserting or updating information in mysql database
    public static int insertUpdateFromSqlQuery(String SqlQueryString) {
        int i = 2;
        try {
            //Checking whether the connection is null or null
            if (connection == null) {
                getConnection();
            }
            //Querying the query
            i = connection.createStatement().executeUpdate(SqlQueryString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return i;
    }
}



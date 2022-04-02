package com.attech.estore.dao;


import com.attech.estore.config.DBconnection;
import com.attech.estore.dto.CustomerDto;
import com.attech.estore.model.Customer;

import java.sql.*;
import java.time.LocalDate;

import static com.attech.estore.config.DBconnection.getConnection;
import static com.attech.estore.config.DBconnection.getResultFromSqlQuery;


public class CustomerDao {
    Connection baseConnection = DBconnection.getConnection();

    public CustomerDto saveCustomer (CustomerDto customerDto) throws SQLException {
        int result = 0;
        CustomerDto customerDto1 = null;
        try (Connection connection = baseConnection;
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into customer (name ,email, gender, address, wallet, created_at, modify_at) values (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, customerDto.getName());
            preparedStatement.setString(2, customerDto.getEmail());
            preparedStatement.setString(3, customerDto.getGender());
            preparedStatement.setString(4, customerDto.getAddress());
            preparedStatement.setDouble(5, customerDto.getWallet());
            preparedStatement.setString(6, LocalDate.now().toString());
            preparedStatement.setString(7, LocalDate.now().toString());

            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();// will return 0 OR 1
            if (result > 0) {
                ResultSet rs  = getResultFromSqlQuery("select * from customer where email = ? ",customerDto.getEmail());
               if (rs.next()){
                   customerDto1 = new CustomerDto();
                   int id = rs.getInt("id");
                   String name = rs.getString("name");
                   String email = rs.getString("email");
                   String password = customerDto.getPassword();
                   String gender = rs.getString("gender");
                   String address = rs.getString("address");
                   double wallet = rs.getDouble("wallet");
                   customerDto1.setId(id);
                   customerDto1.setName(name);
                   customerDto1.setEmail(email);
                   customerDto1.setGender(gender);
                   customerDto1.setPassword(password);
                   customerDto1.setAddress(address);
                   customerDto1.setWallet(wallet);
                   createLogin(id, email, password);
               }
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return customerDto1;
    }

    private void createLogin(int id, String email, String password) {
        try (Connection connection = baseConnection;
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into login (profie_id, email ,password, role, created_at, modify_at) values (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, "customer");
            preparedStatement.setString(5, LocalDate.now().toString());
            preparedStatement.setString(6, LocalDate.now().toString());
            // Step 3: Execute the query or update query
            int result = preparedStatement.executeUpdate();// will return 0 OR 1
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public CustomerDto findByUsernameAndPassword (String email, String password) {
        int i = 0;
        CustomerDto customerDto = new CustomerDto();
        try (
                Connection connection = baseConnection;
        ){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from login where email = '"+email+"' and password = '"+ password+"'");
            while (rs.next()){
                customerDto.setId(rs.getInt("id"));
                customerDto.setEmail(rs.getString("email"));
                customerDto.setPassword(rs.getString("password"));
                customerDto.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDto;
    }
    public Customer updateCustomer () {return null;}
    public Customer deleteCustomer () {return null;}
    public Customer findCustomerById () {return null;}
    public Customer findCustomerByUserName () {return null;}


}

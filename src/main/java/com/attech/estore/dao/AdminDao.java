package com.attech.estore.dao;

import com.attech.estore.config.DBconnection;
import com.attech.estore.dto.ProductDto;
import com.attech.estore.model.Category;
import com.attech.estore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminDao {
    Connection baseConnection = DBconnection.getConnection();
    public boolean insertProduct(ProductDto productDto, String category){
        int affected = 0;
                String query = "INSERT INTO product(name, price, quantity, category_id, url) values (?,?,?,?,?)";
        try(
                PreparedStatement preparedStatement = baseConnection.prepareStatement(query);
                ){
            int categoryId = getProductCategory(category);
            preparedStatement.setString(1,productDto.getName());
            preparedStatement.setDouble(2,productDto.getPrice());
            preparedStatement.setInt(3,productDto.getQuantity());
            preparedStatement.setInt(4,categoryId);
            preparedStatement.setString(5,productDto.getUrl());
            affected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected > 0;
    }

    public List<ProductDto> fetchProducts(){
        List<ProductDto> productDtos = new ArrayList<>();
        String query = "SELECT * FROM `product`" +
                "inner join `category` where `product`.`category_id` = `category`.`categoryId`";
        try(Statement statement = baseConnection.createStatement();){
            ResultSet resultSet= statement.executeQuery(query);
            while (resultSet.next()){
                Category category = new Category();
                ProductDto productDto = new ProductDto();
                productDto.setId(resultSet.getInt("id"));
                productDto.setName(resultSet.getString("name"));
                productDto.setPrice(resultSet.getDouble("price"));
                productDto.setQuantity(resultSet.getInt("quantity"));
                category.setName(resultSet.getString("category_name"));
                productDto.setCategory(category);
                productDto.setUrl(resultSet.getString("url"));
                productDtos.add(productDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productDtos;
    }

    public ProductDto getProductById(int id){
        ProductDto productDto = new ProductDto();
        String query = "SELECT * FROM product where id = ?";
        try(
                PreparedStatement preparedStatement = baseConnection.prepareStatement(query);

        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                productDto.setId(resultSet.getInt("id"));
                productDto.setName(resultSet.getString("name"));
                productDto.setPrice(resultSet.getDouble("price"));
                productDto.setQuantity(resultSet.getInt("quantity"));
                productDto.setUrl(resultSet.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productDto;
    }


    private int getProductCategory(String category) {
        int categoryID = 0;
        String query = "SELECT * FROM category WHERE category_name='"+category+"'";
        try(Statement statement = baseConnection.createStatement();){
            ResultSet resultSet= statement.executeQuery(query);
            while (resultSet.next()){
                categoryID = resultSet.getInt("categoryId");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryID;
    }

    public boolean updateProduct(ProductDto productDto) {
        int result = 0;
        String query = "update product set name=?, price=?, quantity=?, url=? where id=?";
        try(PreparedStatement preparedStatement = baseConnection.prepareStatement(query)){
            preparedStatement.setString(1, productDto.getName());
            preparedStatement.setDouble(2, productDto.getPrice());
            preparedStatement.setInt(3, productDto.getQuantity());
            preparedStatement.setString(4,productDto.getUrl());
            preparedStatement.setInt(5,productDto.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public boolean deleteProduct(int id) {
        int result = 0;
        String query = "delete from product where id=?";
        try(PreparedStatement preparedStatement = baseConnection.prepareStatement(query);){
            preparedStatement.setInt(1, id);
            result  = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public List<ProductDto> filterByCategory(String category){
        List<ProductDto> productDtos = fetchProducts();
      return productDtos.stream().filter(productDto -> productDto.getCategory().getName().equals(category)).collect(Collectors.toList());
    }

    public ProductDto filterById(int id) {
        List<ProductDto> productDtos = fetchProducts();
        return productDtos.stream().filter(p -> p.getId() == id).collect(Collectors.toList()).get(0);
    }
}

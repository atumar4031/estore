package com.attech.estore.dto;

import com.attech.estore.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String url;
    private Category category;

}

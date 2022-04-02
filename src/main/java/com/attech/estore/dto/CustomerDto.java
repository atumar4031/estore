package com.attech.estore.dto;

import com.attech.estore.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Data
public class CustomerDto {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String address;
    private String password;
    private String role;
    private double wallet;
}

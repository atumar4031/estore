package com.attech.estore.model;

import com.attech.estore.enums.Gender;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer extends Human{
    private double wallet;
    private String role;

    public Customer(int id, String name, String gender, String email, String password, String role) {
        super(id, name, gender, email, password);
        this.wallet = 0.00;
        this.role = role;
    }
}

package com.attech.estore.model;

import com.attech.estore.enums.Gender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Human {
    private int id;
    private String name;
    private String gender;
    private String password;
    private String email;
}

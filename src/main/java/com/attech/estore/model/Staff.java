package com.attech.estore.model;

import com.attech.estore.enums.Gender;
import com.attech.estore.enums.Role;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class Staff extends Human{
    private String staffId;
    private String qualification;
    private int performance;

    public Staff(int id, String name, String gender, String email, String staffId, String role, String qualification) {
        super(id, name, gender, email, role);
        this.staffId = staffId;
        this.qualification = qualification;
        this.performance = 0;
    }
}

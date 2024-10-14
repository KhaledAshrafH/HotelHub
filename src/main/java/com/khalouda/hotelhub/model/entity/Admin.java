package com.khalouda.hotelhub.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "adminBuilder")
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    @Override
    public String toString() {
        return "Admin{" +
                "adminLevel='" + adminLevel + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }

    private String adminLevel; // SuperAdmin, Manager
    private String permissions; // admin roles
}

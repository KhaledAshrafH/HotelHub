package com.khalouda.hotelhub.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "userBuilder")
@DiscriminatorValue("STAFF")
public class Staff extends User {
    private String position; // Receptionist, Housekeeper
    private BigDecimal salary;
    private String department; // Front Desk, Maintenance

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "staff")
    private List<Task> tasks;
}

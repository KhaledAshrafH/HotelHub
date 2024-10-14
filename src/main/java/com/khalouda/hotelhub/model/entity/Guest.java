package com.khalouda.hotelhub.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "guestBuilder")
@DiscriminatorValue("GUEST")
public class Guest extends User {
    private String guestToken;
    private String preferences; // room type, amenities
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}

package com.khalouda.hotelhub.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hotel_chains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelChain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_chain_id")
    private Long hotelChainId;

    @Column(unique = true)
    private String name;
    private String description;

    @Column(name = "headquarters_location")
    private String headquartersLocation;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "hotelChain")
    private List<Hotel> hotels;
}

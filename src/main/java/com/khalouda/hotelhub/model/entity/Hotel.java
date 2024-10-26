package com.khalouda.hotelhub.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(unique = true,nullable = false)
    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;
    private String email;
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel")
    private List<Review> reviews;

    @OneToMany(mappedBy = "hotel")
    private List<Staff> staff;

    @OneToMany(mappedBy = "hotel")
    private List<Amenity> amenities;

    @OneToMany(mappedBy = "hotel")
    private List<Discount> discounts;

    @OneToMany(mappedBy = "hotel")
    private List<EventSpace> eventSpaces;

    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "hotel_chain_id")
    private HotelChain hotelChain;
}

package com.khalouda.hotelhub.model.entity;

import com.khalouda.hotelhub.model.enums.RoomsType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "room_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Long roomTypeId;

    @Column(name = "type_name")
    @Enumerated(EnumType.STRING)
    private RoomsType typeName;

    private String description;
    @Column(name = "max_occupancy")
    private int maxOccupancy;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;
}

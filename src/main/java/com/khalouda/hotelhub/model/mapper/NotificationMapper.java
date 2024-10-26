package com.khalouda.hotelhub.model.mapper;


import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.Notification;
import com.khalouda.hotelhub.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationResponseDTO toResponseDTO(Notification notification);
    List<NotificationResponseDTO> toResponseDTOs(List<Notification> notifications);

    Notification toEntity(NotificationCreationDTO notificationCreationDTO);
}

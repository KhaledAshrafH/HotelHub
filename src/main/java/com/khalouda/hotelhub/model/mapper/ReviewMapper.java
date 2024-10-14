package com.khalouda.hotelhub.model.mapper;


import com.khalouda.hotelhub.model.dto.ReviewCreationDTO;
import com.khalouda.hotelhub.model.dto.ReviewResponseDTO;
import com.khalouda.hotelhub.model.dto.ReviewUpdateDTO;
import com.khalouda.hotelhub.model.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewResponseDTO toResponseDTO(Review review);
    List<ReviewResponseDTO> toResponseDTOs(List<Review> reviews);
    ReviewCreationDTO toCreationDTO(Review review);
    ReviewUpdateDTO toUpdateDTO(Review review);

    Review toEntity(ReviewCreationDTO reviewCreationDTO);
    Review toEntity(ReviewUpdateDTO reviewUpdateDTO);
}
package com.nirban.rating.RatingService.dto.responseDto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class RatingDto {

    @Id
    private String ratingId;
    @NotNull(message="user-id is required")
    private String userId;
    @NotNull(message="hotel id is required")
    private String hotelId;
    @NotNull(message="rating is required")
    private  int rating;
    @NotNull(message="feedback is required")
    @Size(min=10)
    private  String feedback;
}

package com.nirban.hotel.HotelService.dto.requestDto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class HotelRequestDto {

    private  String id;
    @NotNull(message="Name required")
    private  String name;
    @NotNull(message = "Location is required")
    private  String location;
    @NotNull(message = "About is required")
    @Size(min = 10, message = "About section must be at least 10 characters long")
    private  String about;

}

package com.cs348.backendservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Response object containing lot information")
public class LotResponse {
    @Schema(description = "Lot ID", example = "123")
    private int lid;

    @Schema(description = "Lot name", example = "DC")
    private String lotName;

    @Schema(description = "Latitude", example = "12.34")
    private float latitude;

    @Schema(description = "longitude", example = "12.34")
    private float longitude;
}

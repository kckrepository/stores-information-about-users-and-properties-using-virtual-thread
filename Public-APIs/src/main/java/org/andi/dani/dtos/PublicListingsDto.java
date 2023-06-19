package org.andi.dani.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PublicListingsDto {
    @JsonProperty("listings")
    private List<PublicListingDto> listPublicListingDto;
    private boolean result;
}

package org.andi.dani.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatedListingDto {
    private boolean result;
    @JsonProperty("listing")
    private ListingDto listingDto;
}

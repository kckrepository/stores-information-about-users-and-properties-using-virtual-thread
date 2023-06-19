package org.andi.dani.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateListingDto {
    @JsonProperty("user_id")
    private  Long userId;
    @JsonProperty("listing_type")
    private String listingType;
    private BigDecimal price;
}

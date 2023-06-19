package org.andi.dani.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PublicListingDto {
    private Long id;
    @JsonProperty("user")
    private UserDto userDto;
    @JsonProperty("listing_type")
    private InfoType listingType;
    private BigDecimal price;
    @JsonProperty("created_at")
    private Date CreatedAt;
    @JsonProperty("updated_at")
    private Date UpdateAt;
}

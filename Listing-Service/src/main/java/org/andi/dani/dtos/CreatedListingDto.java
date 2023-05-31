package org.andi.dani.dtos;

import lombok.Data;

@Data
public class CreatedListingDto {
    private boolean result;
    private ListingDto listingDto;
}

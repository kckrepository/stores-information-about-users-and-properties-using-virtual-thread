package org.andi.dani.dtos;

import lombok.Data;
import java.util.List;

@Data
public class ListingsDto {
    private List<ListingDto> listings;
    private boolean result;
}

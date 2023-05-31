package org.andi.dani.dtos;

import lombok.Data;
import org.andi.dani.entities.InfoType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ListingsDto {
    private List<ListingDto> listings;
    private boolean result;
}

package org.andi.dani.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FormCreateListing {
    private long user_id;
    private BigDecimal price;
    private String listing_type;
}

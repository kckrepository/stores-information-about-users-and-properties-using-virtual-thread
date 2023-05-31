package org.andi.dani.dtos;

import lombok.Data;
import org.andi.dani.entities.InfoType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ListingDto {
    private Long id;
    private Long UserId;
    private InfoType listingType;
    private BigDecimal price;
    private Date CreatedAt;
    private Date UpdateAt;
}

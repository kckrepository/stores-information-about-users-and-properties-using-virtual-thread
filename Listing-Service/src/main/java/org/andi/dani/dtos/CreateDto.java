package org.andi.dani.dtos;

import lombok.Data;
import org.andi.dani.entities.InfoType;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CreateDto {
    private long user_id;
    private BigDecimal price;
    private String listing_type;
}

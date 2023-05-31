package org.andi.dani.controllers;

import lombok.AllArgsConstructor;
import org.andi.dani.dtos.CreateDto;
import org.andi.dani.dtos.CreatedListingDto;
import org.andi.dani.dtos.ListingsDto;
import org.andi.dani.services.InfoPropertyService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ListingController {
    private final InfoPropertyService infoPropertyService;

    @GetMapping("/listings")
    public ResponseEntity<ListingsDto> getListings(@RequestParam(name = "page_num", required = false) Integer pageNum,
                                                   @RequestParam(name = "page_size", required = false) Integer pageSize,
                                                   @RequestParam(name = "user_id", required = false) Long userId) {
        return infoPropertyService.getListings(pageNum, pageSize, userId);
    }

    @PostMapping(path = "/listings", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<CreatedListingDto> createListing(CreateDto createDto) {
        return infoPropertyService.create(createDto);
    }
}

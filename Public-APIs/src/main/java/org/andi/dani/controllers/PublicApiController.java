package org.andi.dani.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andi.dani.dtos.*;
import org.andi.dani.services.PublicApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/public-api")
public class PublicApiController {
    private final PublicApiService publicApiService;
    @GetMapping("/listings")
    public ResponseEntity<PublicListingsDto> getPublicListings(@RequestParam(name = "page_num", required = false) Integer pageNum,
                                                               @RequestParam(name = "page_size", required = false) Integer pageSize,
                                                               @RequestParam(name = "user_id", required = false) Long userId) {
        log.info("Info -> " + Thread.currentThread());
        return publicApiService.getPublicListings(pageNum, pageSize, userId);
    }

    @PostMapping("/users")
    public ResponseEntity<CreatedUserDto> createUser(@RequestBody CreateDto createDto) {
        return publicApiService.createUser(createDto);
    }

    @PostMapping("/listings")
    public ResponseEntity<PublicCreatedListingDto> createListing(@RequestBody CreateListingDto createListingDto) {
        return publicApiService.createListing(createListingDto);
    }
}

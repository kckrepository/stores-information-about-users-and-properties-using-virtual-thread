package org.andi.dani.clients;

import org.andi.dani.dtos.CreatedListingDto;
import org.andi.dani.dtos.FormCreateListing;
import org.andi.dani.dtos.ListingsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "LISTING-SERVICE", url = "http://localhost:8081")
public interface ListingClient {
    @GetMapping("/listings")
    ListingsDto getListings(@RequestParam(name = "page_num") Integer pageNum,
                            @RequestParam(name = "page_size") Integer pageSize,
                            @RequestParam(name = "user_id") Long userId);

    @PostMapping(value="/listings", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    CreatedListingDto createListing(@RequestBody FormCreateListing formCreateListing);
}

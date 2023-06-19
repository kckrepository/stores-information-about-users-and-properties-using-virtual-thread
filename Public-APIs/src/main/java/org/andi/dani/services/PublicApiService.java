package org.andi.dani.services;

import lombok.AllArgsConstructor;
import org.andi.dani.clients.ListingClient;
import org.andi.dani.clients.UserClient;
import org.andi.dani.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicApiService {
    private final ListingClient listingClient;
    private final UserClient userClient;
    public ResponseEntity<PublicListingsDto>  getPublicListings(Integer pageNum, Integer pageSize,
                                                                Long userId) {
        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }

        if (pageNum > 0) {
            pageNum = pageNum - 1;
        }

        if (Objects.isNull(pageSize) || pageSize == 0) {
            pageSize = 10;
        }

        var listingsDto = listingClient.getListings(pageNum, pageSize, userId);
        var list = listingsDto.getListings().stream().map(listingDto -> {
            var publicListingDto = new PublicListingDto();
            publicListingDto.setId(listingDto.getId());
            publicListingDto.setListingType(listingDto.getListingType());
            publicListingDto.setPrice(listingDto.getPrice());
            publicListingDto.setCreatedAt(listingDto.getCreatedAt());
            publicListingDto.setUpdateAt(listingDto.getUpdateAt());

            var userDetailDto = userClient.getUserDetail(listingDto.getUserId());

            publicListingDto.setUserDto(userDetailDto.getUserDto());

            return publicListingDto;
        }).collect(Collectors.toList());

        var publicListingsDto = new PublicListingsDto();
        publicListingsDto.setListPublicListingDto(list);
        publicListingsDto.setResult(true);

        return new ResponseEntity<PublicListingsDto>(publicListingsDto, HttpStatus.OK);
    }

    public ResponseEntity<CreatedUserDto> createUser(CreateDto createDto) {
        if (Objects.isNull(createDto.getName()) || createDto.getName().isBlank()) {
            var createdUserDto = new CreatedUserDto();
            return new ResponseEntity<CreatedUserDto>(createdUserDto, HttpStatus.BAD_REQUEST);
        }

        var userDetailDto = userClient.createUser(createDto);

        var createdUserDto = new CreatedUserDto();
        createdUserDto.setUser(userDetailDto.getUserDto());

        return new ResponseEntity<CreatedUserDto>(createdUserDto, HttpStatus.CREATED);
    }

    public ResponseEntity<PublicCreatedListingDto> createListing(CreateListingDto createListingDto) {
        if (Objects.isNull(createListingDto.getListingType()) || createListingDto.getListingType().isBlank()
                || Objects.isNull(createListingDto.getPrice())) {
            var createdListingDto = new PublicCreatedListingDto();
            return new ResponseEntity<PublicCreatedListingDto>(createdListingDto, HttpStatus.BAD_REQUEST);
        }

        var formCreateListing = new FormCreateListing();
        formCreateListing.setPrice(createListingDto.getPrice());
        formCreateListing.setUser_id(createListingDto.getUserId());
        if (createListingDto.getListingType().equalsIgnoreCase(InfoType.RENT.toString())) {
            formCreateListing.setListing_type(InfoType.RENT.toString());
        }
        else if (createListingDto.getListingType().equalsIgnoreCase(InfoType.SALE.toString())) {
            formCreateListing.setListing_type(InfoType.SALE.toString());
        }
        else {
            var createdListingDto = new PublicCreatedListingDto();
            return new ResponseEntity<PublicCreatedListingDto>(createdListingDto, HttpStatus.BAD_REQUEST);
        }

        var createdListingDto = listingClient.createListing(formCreateListing);

        var publicCreatedListingDto = new PublicCreatedListingDto();
        publicCreatedListingDto.setListing(createdListingDto.getListingDto());

        return new ResponseEntity<PublicCreatedListingDto>(publicCreatedListingDto, HttpStatus.CREATED);
    }
}

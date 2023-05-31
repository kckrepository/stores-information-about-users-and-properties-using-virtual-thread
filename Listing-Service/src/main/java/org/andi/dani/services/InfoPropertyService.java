package org.andi.dani.services;

import lombok.AllArgsConstructor;
import org.andi.dani.dtos.CreateDto;
import org.andi.dani.dtos.CreatedListingDto;
import org.andi.dani.dtos.ListingDto;
import org.andi.dani.dtos.ListingsDto;
import org.andi.dani.entities.InfoProperty;
import org.andi.dani.entities.InfoType;
import org.andi.dani.repositories.InfoPropertyRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InfoPropertyService {
    private final InfoPropertyRepo infoPropertyRepo;

    public ResponseEntity<ListingsDto> getListings(Integer pageNum, Integer pageSize, Long userId) {
        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }

        if (pageNum > 0) {
            pageNum = pageNum - 1;
        }

        if (Objects.isNull(pageSize) || pageSize == 0) {
            pageSize = 10;
        }

        Pageable pageable =
                PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());

        List<ListingDto> list = null;
        if (Objects.isNull(userId)) {
            list = infoPropertyRepo.findAll(pageable).stream().map(infoProperty -> {
                var listingDto = new ListingDto();
                listingDto.setId(infoProperty.getId());
                listingDto.setListingType(infoProperty.getInfoType());
                listingDto.setPrice(infoProperty.getPrice());
                listingDto.setUserId(infoProperty.getUserId());
                listingDto.setCreatedAt(infoProperty.getCreatedAt());
                listingDto.setUpdateAt(infoProperty.getUpdatedAt());
                return listingDto;
            }).collect(Collectors.toList());
        }
        else {
            list = infoPropertyRepo.findAllByUserId(userId, pageable).stream().map(infoProperty -> {
                var listingDto = new ListingDto();
                listingDto.setId(infoProperty.getId());
                listingDto.setListingType(infoProperty.getInfoType());
                listingDto.setPrice(infoProperty.getPrice());
                listingDto.setUserId(infoProperty.getUserId());
                listingDto.setCreatedAt(infoProperty.getCreatedAt());
                listingDto.setUpdateAt(infoProperty.getUpdatedAt());
                return listingDto;
            }).collect(Collectors.toList());
        }

        var listingsDto = new ListingsDto();
        listingsDto.setListings(list);
        listingsDto.setResult(true);

        return new ResponseEntity<ListingsDto>(listingsDto, HttpStatus.OK);
    }

    public ResponseEntity<CreatedListingDto> create(CreateDto createDto) {
        if (Objects.isNull(createDto.getListing_type()) || createDto.getListing_type().isBlank()
        || Objects.isNull(createDto.getPrice())) {
            var createdListingDto = new CreatedListingDto();
            createdListingDto.setResult(false);
            return new ResponseEntity<CreatedListingDto>(createdListingDto, HttpStatus.BAD_REQUEST);
        }

        InfoProperty newInfoProperty = new InfoProperty();
        newInfoProperty.setPrice(createDto.getPrice());
        newInfoProperty.setUserId(createDto.getUser_id());
        if (createDto.getListing_type().equalsIgnoreCase(InfoType.RENT.toString())) {
            newInfoProperty.setInfoType(InfoType.RENT);
        }
        else if (createDto.getListing_type().equalsIgnoreCase(InfoType.SALE.toString())) {
            newInfoProperty.setInfoType(InfoType.SALE);
        }
        else {
            var createdListingDto = new CreatedListingDto();
            createdListingDto.setResult(false);
            return new ResponseEntity<CreatedListingDto>(createdListingDto, HttpStatus.BAD_REQUEST);
        }
        var resultInfoProperty = infoPropertyRepo.save(newInfoProperty);

        var createdListingDto = new CreatedListingDto();
        createdListingDto.setResult(true);
        var listingDto = new ListingDto();
        listingDto.setId(resultInfoProperty.getId());
        listingDto.setListingType(resultInfoProperty.getInfoType());
        listingDto.setPrice(resultInfoProperty.getPrice());
        listingDto.setUserId(resultInfoProperty.getUserId());
        listingDto.setCreatedAt(resultInfoProperty.getCreatedAt());
        listingDto.setUpdateAt(resultInfoProperty.getUpdatedAt());
        createdListingDto.setListingDto(listingDto);
        return new ResponseEntity<CreatedListingDto>(createdListingDto, HttpStatus.CREATED);
    }
}

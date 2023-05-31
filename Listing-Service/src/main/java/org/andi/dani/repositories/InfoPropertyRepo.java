package org.andi.dani.repositories;

import org.andi.dani.entities.InfoProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoPropertyRepo extends PagingAndSortingRepository<InfoProperty, Long> {
    List<InfoProperty> findAllByUserId(long userId, Pageable pageable);
}

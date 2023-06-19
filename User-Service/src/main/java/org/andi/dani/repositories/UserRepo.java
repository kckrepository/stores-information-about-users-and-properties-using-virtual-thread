package org.andi.dani.repositories;

import org.andi.dani.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Long> {
}

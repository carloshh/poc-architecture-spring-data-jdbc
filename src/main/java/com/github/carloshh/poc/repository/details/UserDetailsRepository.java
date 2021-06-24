package com.github.carloshh.poc.repository.details;

import com.github.carloshh.poc.domain.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

    Optional<UserDetails> findByUserId(Long userId);

}

package com.github.carloshh.poc.repository.user;

import com.github.carloshh.poc.domain.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(
            """
                select
                    id, username
                from
                    user
                where
                    username = :username
            """
    )
    Optional<User> findUserByUsername(@Param("username") String username);

}

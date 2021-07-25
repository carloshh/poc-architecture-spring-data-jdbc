package com.github.carloshh.poc.service;

import com.github.carloshh.poc.domain.User;
import com.github.carloshh.poc.domain.UserDetails;
import com.github.carloshh.poc.repository.details.UserDetailsRepository;
import com.github.carloshh.poc.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Transactional
    public User create(String username, String email) {
        var user = userRepository.save(new User(null, username));
        var userDetails = userDetailsRepository.save(new UserDetails(null, user.id(), email));

        LOG.info("user={}, email={}", user, userDetails.getEmail());
        return user;
    }

}

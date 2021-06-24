package com.github.carloshh.poc.domain;

import org.springframework.data.annotation.Id;

public record UserDetails(@Id Long id, Long userId, String email) { }

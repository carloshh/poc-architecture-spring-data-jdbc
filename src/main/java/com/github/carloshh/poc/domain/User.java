package com.github.carloshh.poc.domain;

import org.springframework.data.annotation.Id;

public record User(@Id Long id, String username) { }
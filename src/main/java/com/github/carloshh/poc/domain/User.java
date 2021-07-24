package com.github.carloshh.poc.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_account")
public record User(@Id Long id, String username) { }
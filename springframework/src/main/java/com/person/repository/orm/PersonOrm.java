package com.person.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("persons")
public record PersonOrm (
    @Id
    String id,
    String name,
    LocalDate birthDate,
    boolean active
){
}

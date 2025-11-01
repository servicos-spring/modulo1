package com.person.entity;

import java.time.LocalDate;

public record Person(
        String id,
        String name,
        LocalDate birthDate,
        boolean active
) {
}

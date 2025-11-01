package com.person.controller.dto.response;

import java.time.LocalDate;

public record PersonResponse(
        String id,
        String name,
        LocalDate birthDate,
        boolean active
) {
}

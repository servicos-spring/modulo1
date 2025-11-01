package com.person.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PersonRequest(
        @NotBlank(message = "Informe seu nome")
        String name,

        @NotNull(message = "Informe sua data de nascimento")

        @Past(message = "A data de nascimento não pode ser no futuro")
        LocalDate birthDate
) {
}

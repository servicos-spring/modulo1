package com.person.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("persons")
public record PersonOrm (
    @Id
    String ID,
    String NOME,
    LocalDate DT_NASCIMENTO,
    boolean ATIVO
){
}

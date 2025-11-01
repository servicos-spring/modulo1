package com.person.controller.adapter;

import com.person.controller.dto.request.PersonRequest;
import com.person.controller.dto.response.PersonResponse;
import com.person.entity.Person;

import java.util.UUID;

public class PersonAdapterController {
    private PersonAdapterController(){}

    public static Person cast(PersonRequest person){
        return new Person(
                UUID.randomUUID().toString(),
                person.name(),
                person.birthDate(),
                true
        );
    }

    public static PersonResponse cast(Person person){
        return new PersonResponse(
                UUID.randomUUID().toString(),
                person.name(),
                person.birthDate(),
                true
        );
    }
}

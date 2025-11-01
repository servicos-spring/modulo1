package com.person.repository.adapter;

import com.person.controller.dto.response.PersonResponse;
import com.person.entity.Person;
import com.person.repository.orm.PersonOrm;
import org.springframework.data.domain.Page;

import java.util.List;

public class PersonRepositoryAdapter {
    public static Person cast(PersonOrm person){
        return new Person(
                person.id(),
                person.name(),
                person.birthDate(),
                person.active()
        );
    }

    public static PersonOrm cast(Person person){
        return new PersonOrm(
                person.id(),
                person.name(),
                person.birthDate(),
                person.active()
        );
    }

    public static Page<Person> cast(Page<PersonOrm> perons){
        return perons.map(pessoaOrm ->
                new Person(
                        pessoaOrm.id(),
                        pessoaOrm.name(),
                        pessoaOrm.birthDate(),
                        pessoaOrm.active()
                )
        );
    }
}

package com.person.repository;

import com.person.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonRepository {
    Person save(Person person);

    Page<Person> findAll(Pageable page);

    Person findById(String id);

    void delete(String id);

    Person update(String id, Person person);
}

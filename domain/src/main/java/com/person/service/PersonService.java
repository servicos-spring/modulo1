package com.person.service;

import com.person.entity.Person;
import com.person.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository){
        this.repository = repository;
    }

    public Page<Person> findAll(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Person findById(String id){
        return repository.findById(id);
    }

    public Person save(Person person){
        return repository.save(person);
    }


    public Person update(String id, Person person){
        return repository.update(id, person);
    }

    public void delete(String id){
        repository.delete(id);
    }
}

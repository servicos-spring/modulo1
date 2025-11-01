package com.person.configuration.service;

import com.person.repository.PersonRepository;
import com.person.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonServiceConfg {
    @Bean
    public PersonService pessoaService(PersonRepository repository){
        return new PersonService(repository);
    }
}

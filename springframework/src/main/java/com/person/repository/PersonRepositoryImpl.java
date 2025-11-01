package com.person.repository;

import com.person.entity.Person;
import com.person.exception.BadRequestException;
import com.person.exception.InternalServerException;
import com.person.exception.NotFoundException;
import com.person.repository.adapter.PersonRepositoryAdapter;
import com.person.repository.client.PersonRepositoryWithMongo;
import com.person.repository.orm.PersonOrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    private final PersonRepositoryWithMongo repository;
    private static final Logger LOG = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    public PersonRepositoryImpl(PersonRepositoryWithMongo repository){
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        try {
            boolean exist = repository.findById(person.id()).isPresent();

            if(exist){
                throw new BadRequestException("Pessoa já cadastrada");
            }

            PersonOrm newPerson = repository.save(PersonRepositoryAdapter.cast(person));
            return PersonRepositoryAdapter.cast(newPerson);
        } catch (BadRequestException ex) {
            LOG.error("Erro ao salvar pessoa: {} o erro aconteceu na data/hora: {}", ex.getMessage(), LocalDateTime.now());
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao salvar pessoa: {} o erro aconteceu na data/hora: {}", ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public Person update(String id, Person person) {
        try {
            boolean exist = repository.findByIdAndActiveTrue(id).isPresent();
            if(!exist){
                throw new NotFoundException("Pessoa não encontrada");
            }

            PersonOrm updatedPerson = new PersonOrm(
                    id,
                    person.name(),
                    person.birthDate(),
                    true
            );
            return PersonRepositoryAdapter.cast(repository.save(updatedPerson));
        } catch (NotFoundException ex) {
            LOG.error("Pessoa não encontrada");
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao atualizar pessoa: {} o erro aconteceu na data/hora: {}", ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public Page<Person> findAll(Pageable page) {
        try {
            return PersonRepositoryAdapter.cast(repository.findByActiveTrue(page));
        } catch (Exception ex) {
            LOG.error("Erro ao encontrar pessoas: {} o erro aconteceu na data/hora: {}", ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public Person findById(String id) {
        try{
            boolean exist = repository.findByIdAndActiveTrue(id).isPresent();
            if(!exist){
                throw new NotFoundException("Pessoa não encontrada");
            }

            return PersonRepositoryAdapter.cast(repository.findById(id).get());
        } catch (NotFoundException ex) {
            LOG.error("Pessoa não encontrada");
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao encontrar pessoas: {} o erro aconteceu na data/hora: {}", ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }


    @Override
    public void delete(String id) {
        try {
            Person person = findById(id);

            PersonOrm personOrm = new PersonOrm(
                    id,
                    person.name(),
                    person.birthDate(),
                    false
            );

            repository.save(personOrm);
        } catch (NotFoundException ex) {
            LOG.error("Pessoa não encontrada");
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao apagar pessoas: {} o erro aconteceu na data/hora: {}", ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }
}

package com.person.repository.client;

import com.person.entity.Person;
import com.person.repository.orm.PersonOrm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepositoryWithMongo extends MongoRepository<PersonOrm, String> {
    Page<PersonOrm> findByATIVOTrue(Pageable pageable);
    Optional<PersonOrm> findByIDAndATIVOTrue(String id);
}
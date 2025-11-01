package com.person.controller;

import com.person.controller.adapter.PersonAdapterController;
import com.person.controller.dto.request.PersonRequest;
import com.person.controller.dto.response.PersonResponse;
import com.person.entity.Person;
import com.person.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {
    private PersonService service;

    public PersonController(PersonService service){
        this.service = service;
    }

    @GetMapping("")
    public Page<Person> findAll(@RequestParam int page) {
        return service.findAll(page);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PersonResponse save(@Valid @RequestBody PersonRequest request){
        Person person = PersonAdapterController.cast(request);
        return PersonAdapterController.cast(service.save(person));
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public PersonResponse update(@PathVariable String id, @Valid @RequestBody PersonRequest request){
        Person person = PersonAdapterController.cast(request);
        return PersonAdapterController.cast(service.update(id, person));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public PersonResponse findById(@PathVariable String id){
        return PersonAdapterController.cast(service.findById(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}

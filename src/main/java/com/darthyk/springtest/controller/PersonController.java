package com.darthyk.springtest.controller;

import com.darthyk.springtest.dto.PersonDto;
import com.darthyk.springtest.dto.ShortPersonDto;
import com.darthyk.springtest.model.Person;
import com.darthyk.springtest.service.PersonService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/person")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/short/{id}")
    public ShortPersonDto getShortPersonDto(@PathVariable("id") Long id) {
        return personService.getShortPersonDtoById(id);
    }

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable("id") Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody ShortPersonDto shortPersonDto) {
        return personService.save(shortPersonDto);
    }
}

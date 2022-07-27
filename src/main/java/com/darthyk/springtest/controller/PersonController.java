package com.darthyk.springtest.controller;

import com.darthyk.springtest.dto.PersonDto;
import com.darthyk.springtest.dto.ShortPersonDto;
import com.darthyk.springtest.model.Person;
import com.darthyk.springtest.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "person", description = "The person API")
@RestController
@RequestMapping("/v1/person")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Gets short person DTO", tags = "person")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the person",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            )
    })
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

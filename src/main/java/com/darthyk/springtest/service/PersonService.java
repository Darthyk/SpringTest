package com.darthyk.springtest.service;

import com.darthyk.springtest.dto.PersonDto;
import com.darthyk.springtest.dto.ShortPersonDto;
import com.darthyk.springtest.model.Person;

public interface PersonService {
    ShortPersonDto getShortPersonDtoById(Long id);

    PersonDto getPersonById(Long id);

    Person save(ShortPersonDto shortPersonDto);
}

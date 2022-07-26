package com.darthyk.springtest.service;

import com.darthyk.springtest.dto.PersonDto;
import com.darthyk.springtest.dto.ShortPersonDto;
import com.darthyk.springtest.model.Person;
import com.darthyk.springtest.repository.PersonRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {

    private final static String SEVERAL_PERSON_PATH_PATTERN = "%s.%s";
    private final static String DOT_STRING = "\\.";
    private final static String EMPTY_STRING = "";

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Override public ShortPersonDto getShortPersonDtoById(Long id) {
        return toShortPersonDto(personRepository.findPersonById(id));
    }

    @Override public PersonDto getPersonById(Long id) {
        return toPersonDto(personRepository.findPersonById(id));
    }

    @Override public Person save(ShortPersonDto shortPersonDto) {
        return personRepository.save(toPerson(shortPersonDto));
    }

    private PersonDto toPersonDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setName(person.getName());
        dto.setLastname(person.getLastname());
        dto.setParentNames(getParentNamesByPath(person.getPath()));
        return dto;
    }

    private List<String> getParentNamesByPath(String path) {
        List<Person> personByPath = getPersonByPath(path);
        return personByPath != null ? personByPath.stream()
                .map(Person::getName)
                .collect(Collectors.toList())
                : null;
    }

    private List<Person> getPersonByPath(String path) {
        List<Long> idsByPath = getParentIdsByPath(path);
        if (idsByPath != null && !EMPTY_STRING.equals(idsByPath)) {
            return personRepository.findPersonsById(idsByPath);
        }
        return null;
    }

    private ShortPersonDto toShortPersonDto(Person person) {
        ShortPersonDto dto = new ShortPersonDto();
        dto.setLastname(person.getLastname());
        dto.setName(person.getName());
        dto.setParentId(getLastParentIdByPath(person.getPath()));
        return dto;
    }

    private Person toPerson(ShortPersonDto dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setLastname(person.getLastname());
        person.setPath(getPathByParentId(dto.getParentId()));

        return person;
    }

    private String getPathByParentId(Long parentId) {
        if (parentId != null) {
            String parentPath = personRepository.findParentPathById(parentId);
            if (parentPath != null) {
                return String.format(SEVERAL_PERSON_PATH_PATTERN, parentPath, parentId);
            } else {
                return String.valueOf(parentId);
            }
        }
        return null;
    }

    private Long getLastParentIdByPath(String path) {
        List<Long> parentIds = getParentIdsByPath(path);
        if (parentIds != null) {
            return parentIds.get(parentIds.size() - 1);
        }
        return null;
    }

    private List<Long> getParentIdsByPath(String path) {
        if (path != null || !EMPTY_STRING.equals(path)) {
            return Arrays.stream(path.split(DOT_STRING))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }
        return null;
    }
}

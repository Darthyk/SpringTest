package com.darthyk.springtest.repository;

import com.darthyk.springtest.model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findPersonById(Long id);

    @Query(value = "select p.path from person p where p.id =?1", nativeQuery = true)
    String findParentPathById(Long id);

    @Query(value = "select * from person p where p.id in ?1 group by p.id", nativeQuery = true)
    List<Person> findPersonsById(List<Long> ids);
}

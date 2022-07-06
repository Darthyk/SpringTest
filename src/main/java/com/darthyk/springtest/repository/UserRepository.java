package com.darthyk.springtest.repository;

import com.darthyk.springtest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByNameAndLastname(String name, String lastname);
}

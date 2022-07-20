package com.darthyk.springtest.repository;

import com.darthyk.springtest.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    List<User> findUsersByUsername(String username);
}

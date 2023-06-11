package com.itg.project.repository;

import com.itg.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User,String> {

    boolean existsById(String id);
    Optional<User> findByEmail(String email);
}

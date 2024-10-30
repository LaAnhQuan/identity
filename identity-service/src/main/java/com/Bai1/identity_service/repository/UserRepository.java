package com.Bai1.identity_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bai1.identity_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String username);
}

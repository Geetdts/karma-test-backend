package com.geeth.karma_test_backend.external.repository;

import com.geeth.karma_test_backend.external.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}

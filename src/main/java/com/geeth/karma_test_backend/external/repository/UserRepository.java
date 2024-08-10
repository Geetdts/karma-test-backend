package com.geeth.karma_test_backend.external.repository;

import com.geeth.karma_test_backend.external.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}

package com.lcwd.user.repository;

import com.lcwd.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUserId(String userId);
    User findByEmail(String email);
    void deleteByUserId(String userId);
}

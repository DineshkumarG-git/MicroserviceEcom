package org.dinesh.repository;

import org.dinesh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByEmail(String email);
}

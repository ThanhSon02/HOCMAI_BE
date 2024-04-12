package com.backend.hocmai_be.Repositories;

import com.backend.hocmai_be.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    Boolean existsUserByEmail(String email);

}

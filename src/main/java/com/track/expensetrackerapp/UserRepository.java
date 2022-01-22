package com.track.expensetrackerapp.Repository;

import com.track.expensetrackerapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String name);
}

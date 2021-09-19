package com.dynaccurate.microblog.repository;

import com.dynaccurate.microblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

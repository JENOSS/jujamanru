package com.app.jujamanru.domain.user.repository;

import com.app.jujamanru.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

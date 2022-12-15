package com.example.mylittlebible.domain.user.repository;

import com.example.mylittlebible.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

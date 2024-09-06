package com.example.edufy.repositories;

import com.example.edufy.entities.EdufyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdufyUserRepository extends JpaRepository<EdufyUser, Integer> {
}

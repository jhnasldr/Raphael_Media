package com.example.customerservic.repositories;

import com.example.customerservic.entities.MediaInteractions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaInteractionsRepository extends JpaRepository<MediaInteractions, Integer> {

}

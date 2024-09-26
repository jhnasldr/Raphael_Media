package com.example.customerservic.repositories;

import com.example.customerservic.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByUserName(String userName); //ToDO X
    boolean existsByEmailAdress(String emailAdress); //ToDo X
}

package com.miguel.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguel.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
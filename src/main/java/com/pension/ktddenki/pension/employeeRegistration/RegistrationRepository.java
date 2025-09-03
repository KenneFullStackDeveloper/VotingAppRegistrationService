package com.pension.ktddenki.pension.employeeRegistration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<UserRegistrationModel,Integer> {
    Optional<UserRegistrationModel> findByEmail(String username);
}

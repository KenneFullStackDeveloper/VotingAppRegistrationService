package com.pension.ktddenki.pension.employeeRegistration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
public interface LoginRepository extends JpaRepository<UsertestModel, Integer> {
    UsertestModel findByPassword(String password);
    Optional<UsertestModel> findByEmail(String username);

}
**/
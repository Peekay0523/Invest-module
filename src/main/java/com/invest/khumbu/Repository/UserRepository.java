package com.invest.khumbu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invest.khumbu.Model.User;

public interface UserRepository extends JpaRepository<User, Long> { 
    User findByEmailMobileAndPassword(String emailMobile, String password);
    boolean existsByEmailMobile(String emailMobile);
}


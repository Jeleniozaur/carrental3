package com.jeleniozaur.carrental.user.repository;

import com.jeleniozaur.carrental.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
}

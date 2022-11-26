package com.jeleniozaur.carrental.user.service;

import com.jeleniozaur.carrental.user.model.User;
import com.jeleniozaur.carrental.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        if(userExists(id))
            return userRepository.findById(id).get();
        return null;
    }

    private boolean userExists(Long id) {
        return userRepository.findById(id).isPresent();
    }
}

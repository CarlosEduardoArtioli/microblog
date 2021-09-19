package com.dynaccurate.microblog.service;

import com.dynaccurate.microblog.model.User;
import com.dynaccurate.microblog.repository.UserRepository;
import com.dynaccurate.microblog.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found! Id: " + id + " Type: " + User.class.getName()));
    }
}

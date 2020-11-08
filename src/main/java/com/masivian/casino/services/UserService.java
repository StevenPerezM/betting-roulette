package com.masivian.casino.services;

import com.masivian.casino.models.Bet;
import com.masivian.casino.models.User;
import com.masivian.casino.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {
    private final UserRepository userRepository;

    public User findById(Integer id) throws Exception {
        return userRepository.findById(id).get();
    }

    public List<User> getAll() throws Exception {
        return (List<User>) userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}

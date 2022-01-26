package com.railwayproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String firstName, String lastName, String email, String password) {
        if (email == null && password == null) {
            return null;
        } else {
            if (userRepository.findFirstByEmail(email).isPresent()) {
                System.out.println("this email is already taken");
                return null;
            }
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);
        }
    }

    public User authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}

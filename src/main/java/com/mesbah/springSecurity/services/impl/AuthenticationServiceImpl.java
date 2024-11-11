package com.mesbah.springSecurity.services.impl;

import com.mesbah.springSecurity.dto.SignUpRequest;
import com.mesbah.springSecurity.services.AuthenticationService;
import com.mesbah.springSecurity.entities.Role;
import com.mesbah.springSecurity.entities.User;
import com.mesbah.springSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setSecondName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }
}

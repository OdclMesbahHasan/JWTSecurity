package com.mesbah.springSecurity.services;

import com.mesbah.springSecurity.dto.SignUpRequest;
import com.mesbah.springSecurity.entities.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);

}

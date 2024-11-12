package com.mesbah.springSecurity.services;

import com.mesbah.springSecurity.dto.JwtAuthenticationResponse;
import com.mesbah.springSecurity.dto.RefreshTokenRequest;
import com.mesbah.springSecurity.dto.SignInRequest;
import com.mesbah.springSecurity.dto.SignUpRequest;
import com.mesbah.springSecurity.entities.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}

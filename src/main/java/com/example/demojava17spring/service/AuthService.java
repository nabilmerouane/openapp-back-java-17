package com.example.demojava17spring.service;

import com.example.demojava17spring.dto.LoginRequest;
import com.example.demojava17spring.dto.LoginResponse;
import com.example.demojava17spring.model.Utilisateur;
import com.example.demojava17spring.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;
  private final UtilisateurRepository utilisateurRepository;

  public LoginResponse login(LoginRequest loginRequest) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

    Utilisateur utilisateur =
        utilisateurRepository.findByUsername(loginRequest.username()).orElseThrow();

    String token = jwtService.generateToken(loginRequest.username(), utilisateur.getRole().name());

    return new LoginResponse(token);
  }
}

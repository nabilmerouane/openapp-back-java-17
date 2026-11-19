package com.example.demojava17spring.service;

import com.example.demojava17spring.model.Utilisateur;
import com.example.demojava17spring.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UtilisateurRepository utilisateurRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {

    Utilisateur user =
        utilisateurRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

    return User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .roles(user.getRole().name())
        .build();
  }
}

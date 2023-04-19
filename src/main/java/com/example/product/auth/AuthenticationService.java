package com.example.product.auth;

import com.example.product.dto.AuthenticationRequest;
import com.example.product.dto.AuthenticationResponse;
import com.example.product.dto.RegisterRequest;
import com.example.product.entity.Token;
import com.example.product.entity.User;
import com.example.product.repository.TokenRepo;
import com.example.product.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepo tokenRepo;

    public String register(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .age(request.getAge())
                .gender(request.isGender())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .time_create(com.example.product.service.Utilities.getCurrentDate())
                .build();
        repository.save(user);
        String jwtToken = jwtService.generateToken(((UserDetails) user).getUsername());
        Token token = new Token();
        token.setJwt(jwtToken);
        token.set_provoked(false);
        token.set_expired(false);
        tokenRepo.create(token);
        return jwtToken;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        return generateToken(request);
    }

    public AuthenticationResponse refresh(String jwt){
        Token token = tokenRepo.get(jwt);
        token.set_provoked(true);
        tokenRepo.update(token);
        return generateToken(jwt);
    }

    public AuthenticationResponse generateToken(AuthenticationRequest request){
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(((UserDetails) user).getUsername());
        Token token = new Token();
        token.setJwt(jwtToken);
        token.set_provoked(false);
        token.set_expired(false);
        tokenRepo.create(token);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse generateToken(String jwt){
        String username = jwtService.extractUsername(jwt);
        var jwtToken = jwtService.generateToken(username);
        Token token = new Token();
        token.setJwt(jwtToken);
        token.set_provoked(false);
        token.set_expired(false);
        tokenRepo.create(token);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public String logout(String jwt){
        Token token = tokenRepo.get(jwt);
        token.set_provoked(true);
        tokenRepo.update(token);
        return "logout success";
    }
}

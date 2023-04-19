package com.example.product.auth;

import com.example.product.dto.AuthenticationRequest;
import com.example.product.dto.AuthenticationResponse;
import com.example.product.dto.RefreshRequest;
import com.example.product.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        String token = service.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshRequest request){
        return ResponseEntity.ok(service.refresh(request.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshRequest request){
        return ResponseEntity.ok(service.logout(request.getToken()));
    }

}

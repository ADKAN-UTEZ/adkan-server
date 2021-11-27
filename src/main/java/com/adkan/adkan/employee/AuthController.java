package com.adkan.adkan.employee;

import com.adkan.adkan.config.dto.AuthenticationRequest;
import com.adkan.adkan.config.dto.AuthenticationResponse;
import com.adkan.adkan.config.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdkanEmployeeService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            UserDetails userDetails = service.loadUserByUsername(request.getEmail());
            String jwt = jwtUtil.generateToken(userDetails);
            if (jwt != null){
                Employee employee = service.authenticate(request).get();
                return new ResponseEntity<>(new AuthenticationResponse(jwt, employee), HttpStatus.OK);
            }
        } catch (BadCredentialsException e) {
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }



}

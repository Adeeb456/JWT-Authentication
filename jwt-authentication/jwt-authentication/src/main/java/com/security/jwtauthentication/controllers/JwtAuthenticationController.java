package com.security.jwtauthentication.controllers;

import com.security.jwtauthentication.jwt.JwtHelper;
import com.security.jwtauthentication.model.JwtRequest;
import com.security.jwtauthentication.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){

        doAuthenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);

        //build the response and send
        JwtResponse response = JwtResponse.builder()
                .username(userDetails.getUsername())
                .token(token)
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public void doAuthenticate(String username, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try{
            authManager.authenticate(authentication);
        }
        catch(BadCredentialsException badCredentialsException){
            throw new BadCredentialsException("Invalid Credentials!");
        }
    }
}
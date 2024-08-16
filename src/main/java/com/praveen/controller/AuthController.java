package com.praveen.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
// import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.config.JwtProvider;
import com.praveen.model.User;
import com.praveen.repository.UserRepository;
import com.praveen.request.LoginRequest;
import com.praveen.response.AuthResponse;
import com.praveen.service.CustomUserDetailsImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws Exception{
        User isUserExist = userRepository.findByEmail(user.getEmail());

        if(isUserExist != null){
            throw new Exception("Email already exist with another account");

        }
        User createdUser = new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullname(user.getFullname());

        User savedUser = userRepository.save(createdUser);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setMessage("signup success");
        res.setJwt(jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> signing(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UsernamePasswordAuthenticationToken authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setMessage("signin success");
        res.setJwt(jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    private UsernamePasswordAuthenticationToken authenticate(String username, String password){
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}

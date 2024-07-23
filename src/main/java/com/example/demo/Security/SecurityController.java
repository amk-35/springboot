package com.example.demo.Security;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class SecurityController {


    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserRepository customUserRepository;

    public SecurityController() {
    }

    //can also use @PreAuthorize("hasRole('ADMIN')")
 @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){
     //some database validation service call
    try {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwtToken=JwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }catch (AuthenticationException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.toString());

    }

 }


    @PostMapping("/createNewUser")
    public ResponseEntity createNewUser(@RequestBody LoginRequest request){
        Optional<CustomUser> customUser=customUserRepository.findById(request.getUsername());
        if(customUser.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
        CustomUser user=new CustomUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        customUserRepository.save(user);
        return ResponseEntity.ok("user created");
    }

}

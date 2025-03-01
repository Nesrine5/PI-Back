package com.bezkoder.spring.datajpa.controller.userController;

import com.bezkoder.spring.datajpa.model.userModel.ERole;
import com.bezkoder.spring.datajpa.model.userModel.Role;
import com.bezkoder.spring.datajpa.model.userModel.User;
import com.bezkoder.spring.datajpa.payload.request.LoginRequest;
import com.bezkoder.spring.datajpa.payload.request.SignupRequest;
import com.bezkoder.spring.datajpa.payload.response.JwtResponse;
import com.bezkoder.spring.datajpa.payload.response.MessageResponse;
import com.bezkoder.spring.datajpa.repository.userRepo.RoleRepository;
import com.bezkoder.spring.datajpa.repository.userRepo.UserRepository;
import com.bezkoder.spring.datajpa.security.jwt.JwtUtils;
import com.bezkoder.spring.datajpa.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "student":
          Role studRole = roleRepository.findByName(ERole.ROLE_STUDENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(studRole);

          break;
          case "alumni":
          Role alumRole = roleRepository.findByName(ERole.ROLE_ALUMNI)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(alumRole);

          break;

          case "teacher":
          Role teachRole = roleRepository.findByName(ERole.ROLE_ESPRIT_TEACHER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(teachRole);

          break;
          case "exhibitor":
          Role exhRole = roleRepository.findByName(ERole.ROLE_EXHIBITOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(exhRole);

          break;

        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @GetMapping("/api/user")
  public String getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        long id = getUserId(userDetails);
        String message = "Authenticated user: ID = " + id + ", Username = " + username;
        System.out.println(message);
        return message;
      }
    }
    return "User not authenticated"; // or throw an exception if user not authenticated
  }

  private long getUserId(UserDetails userDetails) {
    // Implement logic to retrieve user ID based on UserDetails
    // For example, if UserDetails is a custom implementation of UserDetails,
    // you might have a method like getUserId() in your UserDetails implementation.
    // Replace this with your actual logic to retrieve the user ID.
    return 0; // Placeholder value
  }
}
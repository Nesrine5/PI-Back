package com.bezkoder.spring.datajpa.controller.userController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

   //for public access 
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }


@GetMapping("/user")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('STUDENT') or hasRole('ALUMNI') or hasRole('ESPRIT_TEACHER') or hasRole('EXHIBITOR') ")
public String userAccess() {
  return "User Content.";
}

@GetMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public String adminAccess() {
  return "Admin Board.";
}

@GetMapping("/student")
@PreAuthorize("hasRole('STUDENT')")
public String studentAccess() {
  return "Student Board.";
}

@GetMapping("/alumni")
@PreAuthorize("hasRole('ALUMNI')")
public String alumniAccess() {
  return "Alumni Board.";
}

@GetMapping("/teacher")
@PreAuthorize("hasRole('ESPRIT_TEACHER')")
public String teacherAccess() {
  return "teacher Board.";
}


@GetMapping("/exhibitor")
@PreAuthorize("hasRole('EXHIBITOR')")
public String exhibitorAccess() {
  return "exhibitor Board.";
}



}
package com.sheryians.major.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    @GetMapping("/login") 
    public String login() {
        GlobalData.cart.clear();
        return "login";
    }
    
    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    /*  @PostMapping("/register")
     public String registerPost(@ModelAttribute("user") User user,HttpServletRequest request)throws ServletException {
         String password=user.getPassword();
         user.setPassword(bCryptPasswordEncoder.encode(password));
         List<Role> roles= new ArrayList<>();
         roles.add(roleRepository.findById(2).get());
         user.setRoles(roles);
        userRepository.save(user);
         request.login(user.getEmail(), password);
         return "redirect:/login";
    }*/
    
  /*   @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
    String email = user.getEmail();
    String password = user.getPassword();
    
    // Perform email and password validation
    if (!isEmailValid(email)) {
        // Invalid email
        // Handle the validation error (e.g., display an error message to the user)
        return "redirect:/register"; // Redirect back to the registration page
    }

    if (!isPasswordValid(password)) {
        // Invalid password
        // Handle the validation error (e.g., display an error message to the user)
        return "redirect:/register"; // Redirect back to the registration page
    }

    // Validation successful, proceed with registration
    user.setPassword(bCryptPasswordEncoder.encode(password));
    List<Role> roles = new ArrayList<>();
    roles.add(roleRepository.findById(2).get());
    user.setRoles(roles);
    userRepository.save(user);
    request.login(user.getEmail(), password);
    return "redirect:/login";
}

public static boolean isEmailValid(String email) {
    // Implement email validation logic
    // Return true if the email is valid, false otherwise
    // You can use regular expressions or any other validation mechanism
    // Example:
    String regex =  "^[A-Za-z0-9+_.-]+@gmail\\.com$";
    return email.matches(regex);
}

public static boolean isPasswordValid(String password) {
    // Implement password validation logic
    // Return true if the password is valid, false otherwise
    // You can define your own password requirements (e.g., minimum length, special characters, etc.)
    // Example:
    int minLength = 5;
    if (password.length() < minLength) {
        
        return false;
    }
  
    //String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{" + minLength + ",}$";
    return true;
}*/ 



@PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String email = user.getEmail();
        String password = user.getPassword();
        
        // Perform email and password validation
        if (!isEmailValid(email) || !isPasswordValid(password)) {
        
            return "redirect:/register"; 
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(), password);
        return "redirect:/login";
    }

    private boolean isEmailValid(String email) {
        // Implement email validation logic
        
        String regex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
        return email.matches(regex);
    }

    private boolean isPasswordValid(String password) {
        // Implement password validation logic
       
        // Example:
        int minLength = 5;
        return password.length() > minLength;
    }
}

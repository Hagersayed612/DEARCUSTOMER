package com.sheryians.major.configuration;

import org.springframework.stereotype.Component;

import com.sheryians.major.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;

import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;

import java.io.IOException;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Component


public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{
        @Autowired
        private final UserRepository UserRepository;
        @Autowired
        private final RoleRepository roleRepository;

        public GoogleOAuth2SuccessHandler(com.sheryians.major.repository.UserRepository userRepository,
                RoleRepository roleRepository) {
            UserRepository = userRepository;
            this.roleRepository = roleRepository;
        }





        private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
        


        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
                throws IOException, ServletException {
                    OAuth2AuthenticationToken token =(OAuth2AuthenticationToken)authentication;
                    String email=token.getPrincipal().getAttributes().get("email").toString();
                    if(UserRepository.findUserByEmail(email).isPresent()){

                    }else{
                        User user =new User();
                        user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
                        user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
                        user.setEmail(email);
                        List<Role> roles= new ArrayList<>();
                        roles.add(roleRepository.findById(2).get());
                        user.setRoles(roles);
                        UserRepository.save(user);


                    }
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
            
        }





        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                Authentication authentication) throws IOException, ServletException {

            AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
        }



}

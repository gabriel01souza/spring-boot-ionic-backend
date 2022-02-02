package com.course.coursemc.services;

import com.course.coursemc.domain.Client;
import com.course.coursemc.repositories.ClientRepository;
import com.course.coursemc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email);
        if(client == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(client.getId(), client.getEmail(), client.getSenha(), client.getPerfis());
    }
}

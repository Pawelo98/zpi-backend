package com.crewmaker.service;

import com.crewmaker.entity.User;

import com.crewmaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userExists = userRepository.findByUsername(username);
        if (userExists != null) {

            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                    userExists.getUsername(), userExists.getPassword(),
                    userExists.isEnabled(), true, true, true, userExists.getAuthorities());
            return user;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


    /*
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByLogin(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user.isAdmin())
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        else
            grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
    */

}

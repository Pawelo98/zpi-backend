package com.crewmaker.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;

}

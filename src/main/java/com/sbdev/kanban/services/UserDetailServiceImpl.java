package com.sbdev.kanban.services;

import com.sbdev.kanban.dao.UserKanbanRepository;
import com.sbdev.kanban.models.Role;
import com.sbdev.kanban.models.UserKanban;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserKanbanRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserKanban user = userRepository.findByUsername(username);
        if(user==null){
            log.error("No existe el usuario {}", username);
            throw new UsernameNotFoundException("No existe el usuario");
        } else {
            log.info("Se encontro el usuario {}", username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:
                user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        log.info("El usuario tiene los siguientes roles {}", user.getRoles());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}

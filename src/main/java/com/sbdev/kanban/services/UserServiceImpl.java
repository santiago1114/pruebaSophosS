package com.sbdev.kanban.services;

import com.sbdev.kanban.dao.RoleRepository;
import com.sbdev.kanban.dao.UserKanbanRepository;
import com.sbdev.kanban.models.Role;
import com.sbdev.kanban.models.UserKanban;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserKanbanRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserKanban> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserKanban getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserKanban saveUser(UserKanban user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Usuario {} guardado",user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public UserKanban addRoleToUser(String username, String rolename) {
        UserKanban user = userRepository.findByUsername(username);
        user.getRoles().add(roleRepository.findByName(rolename));
        log.info("Se agrego el rol {} al usuario {}",rolename,user.getUsername());
        return user;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }


}

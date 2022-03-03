package com.sbdev.kanban.services;

import com.sbdev.kanban.models.Role;
import com.sbdev.kanban.models.UserKanban;

import java.util.List;

public interface UserService {
    List<UserKanban> getUsers();
    UserKanban getUser(String username);
    UserKanban saveUser(UserKanban user);
    UserKanban addRoleToUser(String username, String rolename);
    Role saveRole(Role role);
}

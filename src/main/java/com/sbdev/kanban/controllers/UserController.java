package com.sbdev.kanban.controllers;

import com.sbdev.kanban.models.Role;
import com.sbdev.kanban.models.UserKanban;
import com.sbdev.kanban.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserKanban>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<UserKanban> getUser(@RequestParam(name = "username") String username){
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PostMapping("/user/save")
    public ResponseEntity<UserKanban> saveUser(@RequestBody UserKanban user){
        return ResponseEntity.created(null).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.created(null).body(userService.saveRole(role));
    }

    @PostMapping("/user/addRole")
    public ResponseEntity<UserKanban> addRole(@RequestBody AddRoleRequest addRoleRequest){
        return ResponseEntity.ok()
                .body(userService.addRoleToUser(addRoleRequest.getUsername(),addRoleRequest.getRole()));
    }

}

@Data
class AddRoleRequest {
    private String username;
    private String role;
}
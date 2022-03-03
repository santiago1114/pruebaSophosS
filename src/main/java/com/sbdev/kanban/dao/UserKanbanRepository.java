package com.sbdev.kanban.dao;

import com.sbdev.kanban.models.UserKanban;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserKanbanRepository extends JpaRepository<UserKanban, Integer> {
    UserKanban findByUsername(String username);
}
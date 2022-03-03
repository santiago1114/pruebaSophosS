package com.sbdev.kanban.dao;

import com.sbdev.kanban.models.KanbanBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface KanbanBoardRepository extends JpaRepository<KanbanBoard, Integer> {
    @Override
    Optional<KanbanBoard> findById(Integer integer);

    KanbanBoard findByUserKanban_Username(String username);

}
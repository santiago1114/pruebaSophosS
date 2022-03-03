package com.sbdev.kanban.services;

import com.sbdev.kanban.models.KanbanBoard;

import java.util.List;

public interface KanbanService {
     List<KanbanBoard> getKanbanBoards();
     KanbanBoard getKanbanBoard(int id);
     KanbanBoard getKanbanBoardByUser(String username);
     KanbanBoard modifyKanbanActivities(KanbanBoard k);
     KanbanBoard createKanbanBoard(KanbanBoard k);
}

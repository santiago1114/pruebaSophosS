package com.sbdev.kanban.services;


import com.sbdev.kanban.dao.KanbanBoardRepository;
import com.sbdev.kanban.models.KanbanBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional @Slf4j
@Service
public class KanbanServiceImpl implements KanbanService {
    @Autowired
    private KanbanBoardRepository repository;

    @Override
    public List<KanbanBoard> getKanbanBoards() {
        return repository.findAll();
    }

    @Override
    public KanbanBoard getKanbanBoard(int id) {
        return repository.getById(id);
    }

    @Override
    public KanbanBoard getKanbanBoardByUser(String username) {
        return repository.findByUserKanban_Username(username);
    }

    @Override
    public KanbanBoard modifyKanbanActivities(KanbanBoard k) {

        KanbanBoard kanban = repository.getById(k.getId());
        if (kanban!=null){
            kanban.setDone(k.getDone());
            kanban.setInprogress(k.getInprogress());
            kanban.setTodo(k.getTodo());
        }
        return repository.save(kanban);
    }

    @Override
    public KanbanBoard createKanbanBoard(KanbanBoard k) {

        return repository.save(k);
    }
}

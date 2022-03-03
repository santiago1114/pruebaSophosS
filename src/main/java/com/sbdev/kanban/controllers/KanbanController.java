package com.sbdev.kanban.controllers;

import com.sbdev.kanban.models.*;
import com.sbdev.kanban.services.KanbanService;
import com.sbdev.kanban.services.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/kanban")
//@CrossOrigin(origins = "http://localhost:4200")
public class KanbanController {

    @Autowired
    private KanbanService kanbanService;
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public KanbanBoard saveKanban(@RequestBody ReqKanban reqKanban){
        KanbanBoard kanbanBoard = new KanbanBoard();
        kanbanBoard.setName(reqKanban.getName());
        UserKanban user = userService.getUser(reqKanban.getUsername());
        kanbanBoard.setUserKanban(user);
        return kanbanService.createKanbanBoard(kanbanBoard);
    }

    @GetMapping("/all")
    public List<KanbanBoard> getKanbans(){
        return kanbanService.getKanbanBoards();
    }

    @GetMapping("/get")
    public KanbanBoard getKanban(@RequestParam(required = true) int id){
        KanbanBoard kanbanBoard = kanbanService.getKanbanBoard(id);
        return kanbanBoard;
    }

    @GetMapping("/getByUser")
    public KanbanBoard getKanbanByUsername(@RequestParam(required = true) String username){
        KanbanBoard kanbanBoard = kanbanService.getKanbanBoardByUser(username);
        return kanbanBoard;
    }

    @PostMapping("/modify")
    public KanbanBoard getKanbanAndSave(@RequestBody KanbanBoard kanbanBoard){
        return kanbanService.modifyKanbanActivities(kanbanBoard);
    }

}
@Data
class ReqKanban{
    private String name;
    private String username;
}

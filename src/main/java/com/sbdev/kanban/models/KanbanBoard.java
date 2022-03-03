package com.sbdev.kanban.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "kanban_board")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KanbanBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_kanban_id")
    private UserKanban userKanban;

    @ElementCollection
    @Column(name = "done")
    @CollectionTable(name = "kanban_board_done", joinColumns = @JoinColumn(name = "owner_id"))
    private List<String> done = new ArrayList<>();

    @ElementCollection
    @Column(name = "todo")
    @CollectionTable(name = "kanban_board_todo", joinColumns = @JoinColumn(name = "owner_id"))
    private Set<String> todo = new LinkedHashSet<>();

    @ElementCollection
    @Column(name = "inprogress")
    @CollectionTable(name = "kanban_board_inprogress", joinColumns = @JoinColumn(name = "owner_id"))
    private Set<String> inprogress = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KanbanBoard that = (KanbanBoard) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
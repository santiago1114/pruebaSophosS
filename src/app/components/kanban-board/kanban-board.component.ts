import { Component, HostListener } from '@angular/core';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { NgForm } from '@angular/forms';
import { KanbanService } from 'src/app/services/kanban.service';
import { AuthService } from 'src/app/services/auth.service';
import { Kanban } from 'src/app/models/kanban.model';

@Component({
  selector: 'app-kanban-board',
  templateUrl: './kanban-board.component.html',
  styles: [
    `
      .list {
        min-height: 60px;
        display: block;
        overflow: hidden;
      }

      /* Highlight the list item that is being dragged. */
      .cdk-drag-preview {
        border-radius: 0.5rem;
        box-shadow: 0 5px 5px -3px rgba(0, 0, 0, 0.2),
          0 8px 10px 1px rgba(0, 0, 0, 0.14), 0 3px 14px 2px rgba(0, 0, 0, 0.12);
      }

      /* Animate items as they're being sorted. */
      .cdk-drop-list-dragging .cdk-drag {
        transition: transform 250ms cubic-bezier(0, 0, 0.2, 1);
      }

      /* Animate an item that has been dropped. */
      .cdk-drag-animating {
        transition: transform 300ms cubic-bezier(0, 0, 0.2, 1);
      }

      .cdk-drag-placeholder {
        opacity: 0;
      }
    `,
  ],
})
export class KanbanBoardComponent {
  hint = false;
  name = '';
  existKanban = false;
  kanbanBoard: Kanban = new Kanban(0, '', [], [], []);

  blankActivity = {
    description: '',
    type: '',
  };

  activity: { description: string; type: string };

  constructor(
    private _kanbanService: KanbanService,
    private _auth: AuthService
  ) {
    this._kanbanService.getBoardByUser(this._auth.username$.value).subscribe({
      next: (res) => {
        if (res.id) {
          this.kanbanBoard = new Kanban(
            res.id,
            res.name,
            res.todo,
            res.inprogress,
            res.done
          );
          this.existKanban = true;
        } else this.existKanban = false;
      },
      error: (res) => (this.existKanban = false),
    });

    this.activity = this.blankActivity;
  }

  crearKanban() {
    this._kanbanService.createKanbanBoard(
      this.name,
      this._auth.username$.value
    ).subscribe({
      next: console.log,
      error: console.error  
    })
  }

  onSubmit(form: NgForm) {
    console.log(this.activity);
    if (this.activity.description === '' || this.activity.type === '') {
      this.hint = true;
    } else {
      switch (this.activity.type) {
        case 'todo':
          this.kanbanBoard.todo.push(this.activity.description);
          break;
        case 'inprogress':
          this.kanbanBoard.todo.push(this.activity.description);
          break;
        case 'done':
          this.kanbanBoard.todo.push(this.activity.description);
          break;
        default:
          break;
      }
      form.reset();
      this.hint = false;
      this._kanbanService.updateKanbanBoard(this.kanbanBoard).subscribe({
        next: console.log,
        error: console.error,
      });
    }
  }

  drop(event: CdkDragDrop<string[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
      this._kanbanService.updateKanbanBoard(this.kanbanBoard).subscribe({
        next: console.log,
        error: console.error,
      });
    }
  }
}

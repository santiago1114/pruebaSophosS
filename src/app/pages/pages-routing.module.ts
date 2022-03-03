import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../auth.guard';
import { KanbanComponent } from './kanban/kanban.component';
import { PagesComponent } from './pages.component';

const routes: Routes = [
  {
    path: 'app', 
    component: PagesComponent,
    children: [
      { path: 'kanban', component: KanbanComponent },
      { path: '', pathMatch: 'full', redirectTo: 'app' },
    ],
    canActivate: [AuthGuard]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }

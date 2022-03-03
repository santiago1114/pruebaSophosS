import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { KanbanComponent } from './kanban/kanban.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { PagesComponent } from './pages.component';
import { ComponentsModule } from '../components/components.module';
import {MatInputModule} from '@angular/material/input';

@NgModule({
  declarations: [
    PagesComponent,
    KanbanComponent],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
    ComponentsModule,
    MatInputModule
  ],
  exports: [
    PagesComponent,
    KanbanComponent]
})
export class PagesModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { PagesRoutingModule } from './pages/pages-routing.module';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '**', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
  PagesRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: []
})
export class LoginComponent {
  hide = true;
  credentials = {username: '', password: ''};
  errorLogin = false;
  showModal: boolean = false;

  constructor(private router: Router, private _authService: AuthService) {
  }

  login(form: NgForm) {

    this._authService.doLogin(this.credentials).subscribe({
      next:  res => {
      if (res.access_token) {
          this._authService.authenticated$.next(true);
          this._authService.username$.next(this.credentials.username);
          sessionStorage.setItem('token',res.access_token);
          this.router.navigateByUrl("/app/kanban");
      } else {
        this._authService.authenticated$.next(false);
        this.errorLogin = true;
      }
      }
    ,
      error: (res)=>{
        console.error(res);
        this._authService.authenticated$.next(false);
        this.errorLogin = true;
        form.reset();
      }
      
    });
  }

}


import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  //private urlBase = 'http://localhost:8080/';
  private urlBase = 'http://sbdevsophostest.herokuapp.com/';
  
  username$ = new BehaviorSubject<string>('');
  authenticated$ = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {}

  doLogin(credentials: { username: string; password: string }) {
    const formData = new FormData();
    formData.append('username', credentials.username);
    formData.append('password', credentials.password);

    return this.http.post<any>(this.urlBase + 'login', formData);
  }

  getHeadersWhenLogged() {
    const headers = new HttpHeaders({
      Authorization: 'Token ' + sessionStorage.getItem('token'),
      'Access-Control-Allow-Credentials': 'true',
    });

    return headers;
  }
}

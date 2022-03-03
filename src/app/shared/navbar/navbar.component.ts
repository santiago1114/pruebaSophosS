import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styles: [
  ]
})
export class NavbarComponent implements OnInit {

  open = false;
  username = '';
  constructor(private _auth: AuthService) {
    this.username = this._auth.username$.value;
  }

  ngOnInit(): void {
  }

}

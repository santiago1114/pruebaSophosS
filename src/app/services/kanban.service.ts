import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Kanban } from '../models/kanban.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class KanbanService {

  //private urlBase = 'http://localhost:8080/';
  private urlBase = 'http://sbdevsophostest.herokuapp.com/';
  private uri = 'api/kanban/';

  constructor(private http: HttpClient, private _auth: AuthService) { }

  getBoardByUser(username: string){
    return this.http.get<Kanban>(this.urlBase + this.uri + 'getByUser?username=' + username, {headers: this._auth.getHeadersWhenLogged()});
  }
  
  createKanbanBoard(name: string, username: string){
    return this.http.post<Kanban>(this.urlBase + this.uri + 'save',{name, username}, {headers: this._auth.getHeadersWhenLogged()});
  }

  updateKanbanBoard(kanban: Kanban){
    return this.http.post<Kanban>(this.urlBase + this.uri + 'modify',kanban, {headers: this._auth.getHeadersWhenLogged()});
  }

/*   getKanbanBoard(id:number){
    return this.http.get<Kanban>(this.urlBase+this.uri+'get?id='+id, {headers: 
    this._auth.getHeadersWhenLogged()});
  } */
  
}

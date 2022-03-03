import { Component, HostListener, OnInit } from '@angular/core';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styles: []
})
export class PagesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @HostListener('window:unload', [ '$event' ])
  unloadHandler() {
    
  }
}

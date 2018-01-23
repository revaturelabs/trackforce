import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.css']
})
//Serves as the main container for other components
export class RootComponent implements OnInit {
  title = 'app-root';

  constructor() { }

  ngOnInit() {
  }
}

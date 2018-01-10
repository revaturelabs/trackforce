/**
 * @author Nasir Alauddin, Matthew Snee
 * Nasir - design
 * Matt - implemented services
 */

import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  button: string;
  
  constructor() { }

  ngOnInit() {
    this.button = 'Populate Database';
  }

  populateDB(){
    if (this.button === 'Populate Database'){
      this.button = 'Populate Static SalesForce';
    } else {
      this.button = 'Populate Database';
    }
  }

  refresh(){
    this.button = 'Populate Database';
  }

  
}

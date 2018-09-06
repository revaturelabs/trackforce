import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NameService {

  // private messageSource = new BehaviorSubject('');
  // currentMessage = this.messageSource.asObservable();

  // constructor() { }

  // changeMessage(message: string) {
  //   this.messageSource.next(message)
  // }
  
  constructor(
    private router: Router,
  ) { }

  private data;

  setData(data){
    this.data = data;
  }

  getData(){
    let temp = this.data;
    this.clearData();
    return temp;
  }

  clearData(){
    this.data = undefined;
  }
}
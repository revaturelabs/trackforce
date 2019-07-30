import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class Helpers {

  constructor() {}

public localStorageItem(id: string): string {
    return localStorage.getItem(id);
  }

  public removeStorageItem(id: string){
    localStorage.removeItem(id);
  }

  public localStorageSet(id: string, value: string){
    localStorage.setItem(id, value);
  }

}
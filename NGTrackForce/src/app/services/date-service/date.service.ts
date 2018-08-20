import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  private dateStartSource = new BehaviorSubject(new Date);
  private dateEndSource = new BehaviorSubject(new Date);
  currStartDate = this.dateStartSource.asObservable();
  currEndDate = this.dateEndSource.asObservable();

  constructor() { }

  changeDates(startDate: Date, endDate: Date){
    this.dateStartSource.next(startDate);
    this.dateEndSource.next(endDate);
  }
}

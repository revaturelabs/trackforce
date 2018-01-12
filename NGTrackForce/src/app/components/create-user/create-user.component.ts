/**
 * @author Matt
 */
import {Component, OnInit} from '@angular/core';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  
  user: User;

  constructor() {
    
  }

  ngOnInit() {
    
  }

   
}

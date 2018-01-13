/**
 * @author Matt
 */
import {Component, OnInit} from '@angular/core';
import { User } from '../../models/user.model';
import { CreateUserService } from '../../services/create-user-service/create-user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  
    username: string;
    password: string;
    password2: string;
    roleId: number;
  
    constructor(private userService: CreateUserService) { }
  
    ngOnInit() {
      this.username = 'Username';
      this.password = 'Password';
      this.password2 = 'Re-Enter';
    }
    
    createUser(){
      if(this.password !== this.password2){
        window.alert('Passwords do not match!');
      } else {
        this.userService.createUser(this.username, this.password, this.roleId);
      }
    }
  
     
  }

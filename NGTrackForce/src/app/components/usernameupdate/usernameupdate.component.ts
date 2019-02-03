import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { LocalStorageUtils } from '../../constants/local-storage'
import { UserService } from '../../services/user-service/user.service';
import { CreateUserComponent } from '../create-user/create-user.component'

@Component({
  selector: 'app-usernameupdate',
  templateUrl: './usernameupdate.component.html',
  styleUrls: ['./usernameupdate.component.css']
})
export class UsernameUpdateComponent implements OnInit {
  createUserComp: CreateUserComponent = new CreateUserComponent(null,null,null);
  
  user: User
  userId: number;
  newUsername: string;
  password: string;

  success: string;
  error: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem(LocalStorageUtils.CURRENT_USER_KEY));
    this.userId = this.user.id;
  }

  updateUsername() {
    if(this.createUserComp.validateUserName(this.newUsername)) {
      this.userService.updateUsername(this.userId, this.newUsername, this.password).subscribe(
        data => this.success = 'Username updated successfully',
        err => this.error = "There was an error updating your username"
      );
    } else {
      this.error = "New username is invalid"
    }
  }
}

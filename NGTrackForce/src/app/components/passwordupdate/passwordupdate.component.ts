import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { LocalStorageUtils } from '../../constants/local-storage'
import { UserService } from '../../services/user-service/user.service';
import { CreateUserComponent } from '../create-user/create-user.component'

@Component({
  selector: 'app-passwordupdate',
  templateUrl: './passwordupdate.component.html',
  styleUrls: ['./passwordupdate.component.css']
})
export class PasswordUpdateComponent implements OnInit {
  createUserComp: CreateUserComponent = new CreateUserComponent(null,null,null);
  
  private user: User
  private userId: number;

  private oldPassword: string;
  private newPassword: string;
  private newPasswordConfirm: string;

  private passReq: string = "Minimum 8 characters, number, capital, special character"
  private success: string;
  private error: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem(LocalStorageUtils.CURRENT_USER_KEY));
    this.userId = this.user.id;
  }

  updatePassword() {
    let validNew: boolean = this.createUserComp.validatePassword(this.newPassword)
  
    if((this.newPassword === this.newPasswordConfirm) && validNew) {
      this.userService.updatePassword(this.userId, this.oldPassword, this.newPassword).subscribe(
        data => this.success = 'Password updated successfully',
        err => this.error = "There was an error updating your password"
      );
    } else {
      this.error = "New passwords do not match or are invalid"
    }
  }
}

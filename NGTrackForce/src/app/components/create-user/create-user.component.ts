/**
 * @author Matt Snee
 */
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {User} from '../../models/user.model';
import {UserService} from '../../services/user-service/user.service';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {Router} from '@angular/router';

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
  errMsg: any;
  sucMsg: any;
  newUser: User;
  loggedIn: User;

  constructor(private authService: AuthenticationService,
              private router: Router,
              private userService: UserService) {
  }

  ngOnInit() {
      
  }

  /**
   * Wraps UserService - calls createUser()
   * Sends new user information to service
   *
   */
  createUser() {
    this.errMsg = "";
    this.sucMsg = "";
    if (this.password !== this.password2) {
      this.errMsg = 'Passwords do not match!';
    } else {
      this.newUser = new User(this.username, this.password, this.roleId, 1);
      // this.userService.createUser(this.username, this.password, this.roleId).subscribe(
        console.log(this.newUser);
      this.userService.createUser(this.newUser, this.loggedIn.role).subscribe(
        data => {
          this.sucMsg = 'User created successfully';
        },
        err => {
          console.error("Error Occured", err);
          this.errMsg = 'Error: new user not created!';
        }
      );
    }
  }


}

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
  errMsg: string;
  sucMsg: string;
  userNameError: string;
  newUser: User;
  displayErrorUsername: boolean;
  loggedIn: User;

  readonly _TOKEN = 1;

  constructor(private authService: AuthenticationService,
              private router: Router,
              private userService: UserService) {
  }

  ngOnInit() {

      this.displayErrorUsername = false;
      this.loggedIn = this.authService.getUser();

  }

  /**
   * Wraps UserService - calls createUser()
   * Sends new user information to service
   *
   */
  createUser() {
    this.errMsg = "";
    this.sucMsg = "";
    if (!this._validatePassword(this.password)) {
      this.errMsg = 'Password must have a number, a capital letter and a special character';
    } else if (this.password !== this.password2) {
      this.errMsg = 'Passwords do not match!';
    } else if (!this._validateUserName(this.username)) {
      this.errMsg = 'Invalid username, please do not use spaces or special characters'
    } else {
      this.newUser = new User(this.username, this.password, this.roleId, this._TOKEN);
      // this.userService.createUser(this.username, this.password, this.roleId).subscribe(
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

  private _validateUserName(username): boolean {
    if(!username) {
      return false;
    }
    return username.match(/([^a-zA-Z0-9])/g) == null;
  }

  private _showUserNameError(msg) {
    this.userNameError = msg;
    this.displayErrorUsername = true;
  }

  /**
   * Ensures password follows the password rules
   *
   */
  private _validatePassword(password): boolean {
    if(!password) {
        return false;
    }

    // A password must have a capital, a special char and a number
    const capital = /([A-Z]+)/g
    const special = /([.!@#$%^&*]+)/g
    const num = /([0-9]+)/g

    // Ensures password is valid by ensuring there's at least one match of each regex.
    return password.match(capital) !== null
        && password.match(special) !== null
        && password.match(num) !== null;
  }

  checkUserNameHasValidChars(): boolean {
    if(!this._validateUserName(this.username)) {
      this._showUserNameError('Username cannot have special characters!');
      return false;
    } else {
      this.displayErrorUsername = false;
      return true;
    }
  }

  checkUserNameUnique() {
      //don't even send an HTTP request if username is invalid
      if(!this.checkUserNameHasValidChars()) {
        return;
      }
      this.displayErrorUsername = false;
      this.userService.checkUniqueUsername(this.username).subscribe(
          data => {
              if (data["result"] === 'false') {
                this._showUserNameError('Username is not unique!');
              } else {
                this.displayErrorUsername = false;
              }
          }, err => {
              console.error(err);
          }
      );
  }


}

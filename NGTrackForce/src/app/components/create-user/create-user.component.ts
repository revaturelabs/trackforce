/**
 * @author Matt Snee
 */
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {User} from '../../models/user.model';
import {UserService} from '../../services/user-service/user.service';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {Router} from '@angular/router';
import { StatusMessage } from './create-user.enum';

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
      this.errMsg = StatusMessage.INVALID_PASS;
    } else if (this.password !== this.password2) {
      this.errMsg = StatusMessage.MISMATCH;
    } else if (!this._validateUserName(this.username)) {
      this.errMsg = StatusMessage.INVALID_USER;
    } else {
      this.newUser = new User(this.username, this.password, this.roleId, this._TOKEN);
      // this.userService.createUser(this.username, this.password, this.roleId).subscribe(
      this.userService.createUser(this.newUser, this.loggedIn.role).subscribe(
        data => {
          this.sucMsg = StatusMessage.SUCCESS;
        },
        err => {
          console.error("Error Occured: ", err);
          this.errMsg = StatusMessage.ERROR;
        }
      );
    }
  }

  /**
   * Uses regex to ensure the username follows the rules
   * No special characters
   * Also ensures that the username exists
   * @param username
   */
  private _validateUserName(username): boolean {
    if(!username) {
      return false;
    }
    return username.match(/([^a-zA-Z0-9])/g) == null;
  }

  /**
   * Displays username error
   */
  private _showUserNameError(msg: string) {
    this.userNameError = msg;
    this.displayErrorUsername = true;
  }

  /**
   * Ensures password follows the password rules
   */
  private _validatePassword(password: string): boolean {
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

  /**
   * Toggles display message if password is invalid
   */
  checkValidPassword(password: string): string {
    this.errMsg = this._validatePassword(password) ? '' : StatusMessage.INVALID_PASS;
    return this.errMsg;
  }

  /**
   * Toggles display message if password2 doesn't match password
   * Must also check if password is valid otherwise it might erase error message if password is invalid
   */
  checkConfirmedPassword(password2: string) {
    this.errMsg = this.password === password2 ? this.checkValidPassword(this.password)
                                              : StatusMessage.MISMATCH;
  }

  /**
   * Toggles display of error message if username is invalid
   */
  checkUserNameHasValidChars(): boolean {
    if(!this._validateUserName(this.username)) {
      this._showUserNameError(StatusMessage.INVALID_USER);
      return false;
    } else {
      this.displayErrorUsername = false;
      return true;
    }
  }

  /**
   * Ensures username is unique by sending an HTTP request to see if the username is already used.
   */
  checkUserNameUnique() {
      //don't even send an HTTP request if username is invalid
      if(!this.checkUserNameHasValidChars()) {
        return;
      }
      this.displayErrorUsername = false;
      this.userService.checkUniqueUsername(this.username).subscribe(
          data => {
              if (data["result"] === 'false') {
                this._showUserNameError(StatusMessage.NONUNIQUE);
              } else {
                this.displayErrorUsername = false;
              }
          }, err => {
              console.error(err);
          }
      );
  }

  /**
   * Keep the submit button disabled until the fields have valid values
   */
  toggleSubmitButton(): boolean {
    const validUsername = this._validateUserName(this.username);
    const validPassword = this._validatePassword(this.password) && this.password === this.password2;
    const validRole = this.roleId !== undefined && this.roleId > 0 && this.roleId <= 5;

    return !(validUsername && validPassword && validRole);
  }

}

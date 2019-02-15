import { RequestService } from './../../services/request-service/request.service';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { NavbarService } from '../../services/navbar-service/navbar.service';

@Component({
  selector: 'app-invalid-session',
  templateUrl: './invalid-session.component.html',
  styleUrls: ['./invalid-session.component.css'],
  providers:[ AuthenticationService ]
})
export class InvalidSessionComponent implements OnInit {

  constructor(private authService: AuthenticationService, private navbarService: NavbarService) { }

  ngOnInit() {
    try{
      this.navbarService.hide();
    } catch(error) {
      console.error("Error in invalid-session.component.ts ngOnInit(): ", error)
    }
  }

  toLogin() {
    try{
      this.authService.logout();
    } catch(error) {
      console.error("Error in invalid-session.component.ts toLogin(): ", error)
    }
  }
}

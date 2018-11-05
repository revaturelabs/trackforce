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
    this.navbarService.hide();
  }

  toLogin() {
    this.authService.logout();
  }
}

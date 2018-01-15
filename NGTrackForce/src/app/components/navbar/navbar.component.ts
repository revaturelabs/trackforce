import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: User;
  isAdmin: boolean;
  isAssociate: boolean;
  u: string;

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    this.u = this.authService.getUser();
    this.user = JSON.parse(this.u);

    if(this.user.tfRoleId === 1){
      this.isAdmin = true;
    } else {
      this.isAdmin = false;
    }

    if(this.user.tfRoleId === 4){
      this.isAssociate = true;
    } else {
      this.isAssociate = false;
    }


  }

  logout(){
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }

}
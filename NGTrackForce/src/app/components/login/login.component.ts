import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //url to REST endpoint
  public loginUrl = environment.url + 'TrackForce/track/user/submit';
  public username;
  public password;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.authService.logout();
  }

  //login user service
  login(){
    this.authService.login(this.username, this.password, this.loginUrl).subscribe(
      data => {
        console.log(data)
        //navigate to home page if return is valid
        this.router.navigate(['home']);
      },
      err => {
        console.log(err + " Error Occurred");
        this.authService.logout();
      }
    );
  }

}

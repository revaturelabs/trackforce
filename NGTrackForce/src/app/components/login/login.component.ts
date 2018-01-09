import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //url to REST endpoint on EC2
  public loginUrl = 'http://ec2-54-209-164-28.compute-1.amazonaws.com:8080/TrackForce/track/user/submit';

  constructor() { }

  ngOnInit() {
  }

}

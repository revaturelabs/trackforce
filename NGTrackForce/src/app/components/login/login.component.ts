import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginUrl = 'http://localhost:3000/TrackForce/track/user/submit';

  constructor() { }

  ngOnInit() {
  }

}

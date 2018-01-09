import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //url to REST endpoint; port may need to be changed according to local settings
  public loginUrl = 'http://localhost:3000/TrackForce/track/user/submit';

  constructor() { }

  ngOnInit() {
  }

}

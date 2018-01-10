import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //url to REST endpoint on EC2
  public loginUrl = environment.url + 'TrackForce/track/user/submit';

  constructor() { }

  ngOnInit() {
  }

}

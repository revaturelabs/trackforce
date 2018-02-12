import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
/**
*@author Michael Tseng
*
*@description This is the view for associates only
*
*/
@Component({
  selector: 'app-associate-view',
  templateUrl: './associate-view.component.html',
  styleUrls: ['./associate-view.component.css']
})
@AutoUnsubscribe
export class AssociateViewComponent implements OnInit {

  public associate: Associate = new Associate();


  constructor(private associateService: AssociateService,
    private authService: AuthenticationService,
    private activated: ActivatedRoute) { }

  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    let id = +this.activated.snapshot.paramMap.get('id');
    this.getAssociate(id);
  }

  /**
  *@description Wraps the associate service to get associate info
  *
  *@param {number} id
  *the id number of the associate
  */
  getAssociate(id: number){
    this.associateService.getAssociate(id).subscribe(
      data => {
        this.associate = data;
      },
      err => {
        console.log(err);
      });
  }

  /**
  *@description
  * for logging out the associates
  * clears the localStorage
  */
  logout(){
    this.authService.logout();
  }

}

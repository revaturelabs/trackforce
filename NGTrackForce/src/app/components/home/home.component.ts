import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../services/request-service/request.service';


import 'rxjs/add/operator/map';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  /**
 * http://usejsdoc.org/
 */

  private dbMessage: string;
  private myStatus: string;

  constructor(private rs: RequestService) { }

  ngOnInit() {
  }

  /**
   * @function populateDB
   * @description Populates the database with information from
   *              data script
   */
  populateDB() {
    this.rs.populateDB().map(response => {
      this.dbMessage = response.data;
      this.myStatus = response.status;
    }).map(response => {
      window.location.reload();
    }).subscribe(response => {
      console.log(this.myStatus);
      console.log(this.dbMessage);
    });
  }

  /**
   * @function deleteDB
   * @memberof mainApp.databaseCtrl
   * @description Truncates all the tables in the database
   */
  deleteDB() {
    this.rs.deleteDB().map(response => {
      this.myStatus = response.status;
      this.dbMessage = response.data;
    }).subscribe(response => {
      console.log(this.myStatus);
      console.log(this.dbMessage);
    })
  }

  /**
   * @function populateDBSF
   * @memberof mainApp.databaseCtrl
   * @description SF Populates the database with information
   *              from data script
   */
  populateDBSF() {
    this.rs.populateDBSF().map(response => {
      this.myStatus = response.status;
      this.dbMessage = response.data;
    }).map(response => {
      window.location.reload();
    }).subscribe(response => {
      console.log(this.myStatus);
      console.log(this.dbMessage);
    })
  }

  /**
   * @function InitForce
   * @memberof mainApp.databaseCtrl
   * @description Cleates the cache for the homepage and client page
   */
  initForce() {
    this.rs.initForce();
  }

  /**
   * @function Refresh
   * @memberof mainApp.databaseCtrl
   * @description Used to refresh the page for the database button functions
   */
  refresh() {
    window.location.reload();
  }
}

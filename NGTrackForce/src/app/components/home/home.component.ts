import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../services/request-service/request.service';
import { DataSyncService } from '../../services/datasync-service/data-sync.service';
import { ChartsModule, Color } from 'ng2-charts';

import 'rxjs/add/operator/map';
import { Router } from '@angular/router';
import {ThemeConstants} from '../../constants/theme.constants';
import {ChartOptions} from '../../models/ng2-charts-options.model';
import '../../constants/selected-status.constants';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';

const MONTHS_3 = 788923800;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent {

  /**
 * http://usejsdoc.org/
 */

 //Message from the back-end
  dbMessage: string;
  myStatus: string;
  username: string;
  labels = [];
  data = [];
  amountType: any;

  //Variables for chart settings
  undeployedLabels = SelectedStatusConstants.UNDEPLOYED_LABELS;
  mappedLabels = SelectedStatusConstants.MAPPED_LABELS;
  unmappedLabels = SelectedStatusConstants.UNMAPPED_LABELS;
  deployedLabels = SelectedStatusConstants.DEPLOYED_LABELS;

  mappedColors: Array<Color> = ThemeConstants.MAPPED_COLORS;
  clientColors: Array<Color> = ThemeConstants.CLIENT_COLORS;
  skillColors: Array<Color> = ThemeConstants.SKILL_COLORS;

  deployedChartType = "pie";
  undeployedChartType = "pie";
  mappedChartType = "pie";
  unmappedChartType = "pie";

  private options = ChartOptions.createOptionsLegend('right');

  unmappedOptions = ChartOptions.createOptionsTitle('Unmapped', 24, '#121212', 'right');
  mappedOptions = ChartOptions.createOptionsTitle('Mapped', 24, '#121212', 'right');
  deployedOptions = ChartOptions.createOptionsTitle('Mapped vs. Unmapped (Deployed)', 24, '#121212', 'right');
  undeployedOptions = ChartOptions.createOptionsTitle('Mapped vs. Unmapped (Not Deployed)', 24, '#121212', 'right');
  //end of chart settings

  // populate with dummy data to enable chart labels by default
  private undeployedData: number[] = [0, 0];
  private deployedData: number[] = [0, 0];
  private mappedData: number[] = [0, 0, 0, 0];
  private unmappedData: number[] = [0, 0, 0, 0];
  

  /**
    *@param {RequestService} rs
    * Service for handling requests to the back-end
    *
    *@param {DataSyncService} ds
    * Experimental service with BehaviorSubject
    * BehaviorSubject allows for real-time update of charts
    * Not fully implemented, so it is un-used
    *
    *@param {Router} rout
    * Allows for re-direction to other components
    */
  constructor(private rs: RequestService, private ds: DataSyncService, private rout: Router) { }

  ngOnInit() {
    this.load();
  }

  load() {
    this.rs.getTotals().subscribe(response => {
      /**
       * @member {Array} UndeployedData
       * @description UndeployedData is an array used to populate the
       * dataset of the Undeployed chart. The dataset contains two numbers:
       * the mapped number is the sum of all mapped associates, the unmapped number
       * is the sum of all unmapped associates.
       */
      let undeployedArr: number[] = [response.trainingMapped
        + response.reservedMapped
        + response.selectedMapped
        + response.confirmedMapped,
      response.trainingUnmapped
      + response.openUnmapped
      + response.selectedUnmapped
      + response.confirmedUnmapped];
      this.undeployedData = undeployedArr;

      /**
       * @member {Array} MappedData
       * @description MappedData is an array that stores the
       * data for the dataset of the Mapped chart.
       * The dataset contains four numbers: training mapped<br>
       * reserved mapped <br>
       * selected mapped <br>
       * confirmed mapped<br>
       */
      let mappedArr: number[] = [response.trainingMapped,
      response.reservedMapped,
      response.selectedMapped,
      response.confirmedMapped];
      this.mappedData = mappedArr;

      /**
       * @member {Array} UnmappedData
       * @description UnmappedData is an array that stores the
       * data for the dataset of the Unmapped chart.
       * The dataset contains four numbers: training unmapped<br>
       * open unmapped <br>
       * selected unmapped <br>
       * confirmed unmapped<br>
       */
      let unmappedArr: number[] = [response.trainingUnmapped,
      response.openUnmapped,
      response.selectedUnmapped,
      response.confirmedUnmapped];
      this.unmappedData = unmappedArr;

      /**
       * @member {Array} DeployedData
       * @description DeployedData is an array used to populate the
       * dataset of the Deployed chart. The dataset contains two numbers:
       * the mapped number is the sum of all mapped associates, the unmapped number
       * is the sum of all unmapped associates. Both numbers contain only deployed associates.
       */
      let deployedArr = [response.deployedMapped,
      response.deployedUnmapped];
      this.deployedData = deployedArr;
    });
  }

  /**
* @function MappedOnClick
* @description When the "Mapped" chart is clicked
* the global variable selectedStatus is
* set to the label of the slice
* clicked.
*/
  mappedOnClick(evt: any) {
    if (evt.active[0] != undefined) {
      //navigate to client-mapped component
      this.rout.navigate([`client-mapped/${evt.active[0]._index}`]);
    }
  };
  /**
   * @function UnmappedOnClick
   * @description When the "Unmapped" chart is clicked
   * the global variable selectedStatus is
   * set to the label of the slice
   * clicked.
   */
  unmappedOnClick(evt: any) {
    if (evt.active[0] != undefined) {
      //navigate to skillset component
      this.rout.navigate([`skillset/${evt.active[0]._index}`]);
    }
  }

  /**
   * @function populateDB
   * @description Populates the database with information from
   *              data script
   */
  populateDB() {
    this.rs.populateDB().subscribe(response => {
      this.load();
    }, err => {
      console.log("err");
    });
  }

  /**
   * @function deleteDB
   * @description Truncates all the tables in the database
   */
  deleteDB() {
    this.rs.deleteDB().subscribe(response => {
      this.load();
    }, err => {
      console.log("err");
    })
  }

  /**
   * @function populateDBSF
   * @description SF Populates the database with information
   *              from data script
   * For Salesforce data integration
   */
  populateDBSF() {
    this.rs.populateDBSF().subscribe(response => {
      this.load();
    }, err => {
      console.log("err");
    });
  }

  /**
  * http://usejsdoc.org/
  */

  /**
   * @function getUsername
   * @description This function will return a JavaScript object that contains
   *				the username for the current user that logs in
   */
  getUsername() {
    this.rs.getUsername().subscribe(response => {
      this.username = response.data;
    });
  };

  /**
   * @function defaultBatches
   * @description This function will return a JavaScript object that contains
   * 				all the batches between a 6 month period used in the batch list
   * 				page. We declare and initiate it in the index to preload this
   * 				information to reduce loading on batch list page
   */
  defaultBatches() {
    this.rs.getBatches(this.threeMonthsBefore(), this.threeMonthsAfter()).subscribe(response => {
      // try to get rid of this variable
      // fetched data should be fetched, not stored
      // this.glo.batches = response.data;
    }, () => {
      console.log('Error in doing http request')
    });
  }

  /**
   * @function getCountPerBatchTypeDefault
   * @description This function will return a JavaScript object that contains
   * 				amount of associates per batch type(skillset) within a 6 month
   * 				period to populate graph in batch list
   */
  getCountPerBatchTypeDefault() {
    this.rs.getBatchPerType(this.threeMonthsBefore(), this.threeMonthsAfter()).subscribe(response => {
      // this callback will be called asynchronously
      // when the response is available
      this.labels = [];
      this.data = [];
      let amountType = response.data;
      for (let i = 0; i < amountType.length; i++) {
        this.labels.push(amountType[i].curriculum);
        this.data.push(amountType[i].value);
      }

        this.options = ChartOptions.createOptionsLegend('right');
      }, () => {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      this.amountType = {
        "JTA_SDET": "2",
        ".NET": "3"
      }
    });
  }

  private threeMonthsBefore(): number {
    return new Date().getTime() - MONTHS_3;
  }

  private threeMonthsAfter(): number {
    return new Date().getTime() + MONTHS_3;
  }

  public getUndeployedData(): number[] {
    return this.undeployedData;
  }

  public getDeployedData(): number[] {
    return this.deployedData;
  }

  public getMappedData(): number[] {
    return this.mappedData;
  }

  public getUnmappedData(): number[] {
    return this.unmappedData;
  }
}

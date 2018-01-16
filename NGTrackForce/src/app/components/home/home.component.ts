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

  private dbMessage: string;
  private myStatus: string;
  private username: string;
  private labels = [];
  private data = [];
  private amountType: any;

  private undeployedLabels = SelectedStatusConstants.UNDEPLOYED_LABELS;
  private mappedLabels = SelectedStatusConstants.MAPPED_LABELS;
  private unmappedLabels = SelectedStatusConstants.UNMAPPED_LABELS;
  private deployedLabels = SelectedStatusConstants.DEPLOYED_LABELS;

  private mappedColors: Array<Color> = ThemeConstants.MAPPED_COLORS;
  private clientColors: Array<Color> = ThemeConstants.CLIENT_COLORS;
  private skillColors: Array<Color> = ThemeConstants.SKILL_COLORS;

  deployedChartType = "pie";
  undeployedChartType = "pie";
  mappedChartType = "pie";
  unmappedChartType = "pie";

  private options = ChartOptions.createOptionsLegend('right');

  private unmappedOptions = ChartOptions.createOptionsTitle('Unmapped', 24, '#121212', 'right');
  private mappedOptions = ChartOptions.createOptionsTitle('Mapped', 24, '#121212', 'right');
  private deployedOptions = ChartOptions.createOptionsTitle('Mapped vs. Unmapped (Deployed)', 24, '#121212', 'right');
  private undeployedOptions = ChartOptions.createOptionsTitle('Mapped vs. Unmapped (Not Deployed)', 24, '#121212', 'right');

  // populate with dummy data to enaable chart labels by default
  private undeployedData: number[] = [0, 0];
  private mappedData: number[] = [0, 0, 0, 0];
  private unmappedData: number[] = [0, 0, 0, 0];
  private deployedData: number[] = [0, 0, 0, 0];

  constructor(private rs: RequestService, private ds: DataSyncService, private rout: Router) { }

  ngOnInit() {
    this.load();
  }

  load() {
    console.log("LOADING...");
    this.rs.getTotals().subscribe(response => {
    console.log(response);
      /**
       * @member {Array} UndeployedData
       * @memberof mainApp.mainCtrl
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
       * @memberof mainApp.mainCtrl
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
       * @memberof mainApp.mainCtrl
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
       * @memberof mainApp.mainCtrl
       * @description DeployedData is an array used to populate the
       * dataset of the Deployed chart. The dataset contains two numbers:
       * the mapped number is the sum of all mapped associates, the unmapped number
       * is the sum of all unmapped associates. Both numbers contain only deployed associates.
       */
      let deployedArr = [response.deployedMapped,
      response.deployedUnmapped];
      this.deployedData = deployedArr;
      console.log("LOADED");
      console.log(this.undeployedData);
      console.log(this.mappedData);
      console.log(this.unmappedData);
      console.log(this.deployedData);
    });
  }

  /**
* @function MappedOnClick
* @memberof mainApp.mainCtrl
* @description When the "Mapped" chart is clicked
* the global variable selectedStatus is
* set to the label of the slice
* clicked. The window then loads the
* clientMapped.html partial.
*/
  mappedOnClick(evt: any) {
    if (evt.active[0] != undefined) {
      console.log(evt.active[0]);
      this.rout.navigate([`client-mapped/${evt.active[0]._index}`]);
    }
  };
  /**
   * @function UnmappedOnClick
   * @memberof mainApp.mainCtrl
   * @description When the "Unmapped" chart is clicked
   * the global variable selectedStatus is
   * set to the label of the slice
   * clicked. The window then loads the
   * skillset.html partial.
   */
  unmappedOnClick(evt: any) {
    if (evt.active[0] != undefined) {
      console.log(evt.active[0]);
      this.rout.navigate([`skillset/${evt.active[0]._index}`]);
    }
  }

  /**
   * @function populateDB
   * @description Populates the database with information from
   *              data script
   */
  populateDB() {
    console.log("POPULATING DB...");
    this.rs.populateDB().subscribe(response => {
      console.log("POPULATED DB");
      this.load();
      // console.log(response.status);
    }, err => {
      console.log("err");
    });
  }

  /**
   * @function deleteDB
   * @memberof mainApp.databaseCtrl
   * @description Truncates all the tables in the database
   */
  deleteDB() {
    console.log("TRUNCATING...");
    this.rs.deleteDB().subscribe(response => {
      console.log("TRUNCATED");
      this.load();
      // console.log(response.status);
    }, err => {
      console.log("err");
    })
  }

  /**
   * @function populateDBSF
   * @memberof mainApp.databaseCtrl
   * @description SF Populates the database with information
   *              from data script
   */
  populateDBSF() {
    console.log("POPULATING SF...");
    this.rs.populateDBSF().subscribe(response => {
      console.log("POPULATED SF");
      this.load();
      // console.log(response.status);
    }, err => {
      console.log("err");
    });
  }

  /**
  * http://usejsdoc.org/
  */

  /**
   * @function getUsername
   * @memberof mainApp.indexCtrl
   * @description This function will return a JavaScript object that contains
   *				the username for the current user that logs in
   */
  getUsername() {
    this.rs.getUsername().subscribe(response => {
      this.username = response.data;
      console.log(this.username);
    })
  };

  /**
   * @function defaultBatches
   * @memberof mainApp.indexCtrl
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
   * @memberof mainApp.indexCtrl
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

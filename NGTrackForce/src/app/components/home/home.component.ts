import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../services/request-service/request.service';
import { GlobalsService } from '../../services/globals-service/globals.service';
import { ChartsModule, Color } from 'ng2-charts';

import 'rxjs/add/operator/map';
import { Router } from '@angular/router';

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

  private undeployedLabels = ["Mapped", "Unmapped"];
  private mappedLabels = ['Training', 'Reserved', 'Selected', 'Confirmed'];
  private unmappedLabels = ['Training', 'Open', 'Selected', 'Confirmed'];
  private deployedLabels = ['Mapped', 'Unmapped'];

  private mappedColors: Array<Color> = [{ backgroundColor: ['#ff8d3f', '#514f4f'] }];
  private clientTheme: Array<Color> = [{ backgroundColor: ['#68a225', '#506d2f', '#324851', '#b3de81', '#7da3a1', '#a2c523', '#6e6702', '#2e4600'] }];
  private skillTheme: Array<Color> = [{ backgroundColor: ['#004d47', '#00cffa', '#52958b', '#008dcb', '#b2dbd5', '#6eb5c0', '#006c84', '#113743'] }];

  deployedChartType = "pie";
  undeployedChartType = "pie";
  mappedChartType = "pie";
  unmappedChartType = "pie";

  private options = {
    legend: {
      display: true,
      position: 'right'
    }
  };

  private unmappedOptions = {
    legend: {
      display: true,
      position: 'right'
    },
    title: {
      display: true,
      text: "Unmapped",
      fontSize: 24,
      fontColor: '#121212'

    }
  };

  private mappedOptions = {
    legend: {
      display: true,
      position: 'right'
    },
    title: {
      display: true,
      text: 'Mapped',
      fontSize: 24,
      fontColor: '#121212'
    }
  };

  private deployedOptions = {
    legend: {
      display: true,
      position: 'right'
    },
    title: {
      display: true,
      text: 'Mapped vs. Unmapped (Deployed)',
      fontSize: 24,
      fontColor: '#121212'

    }
  };

  private undeployedOptions = {
    legend: {
      display: true,
      position: 'right'
    },
    title: {
      display: true,
      text: 'Mapped vs. Unmapped (Not Deployed)',
      fontSize: 24,
      fontColor: '#121212'
    }
  };

  // populate with dummy data to enaable chart labels by default
  private undeployedData: number[] = [0, 0];
  private mappedData: number[] = [0, 0, 0, 0];
  private unmappedData: number[] = [0, 0, 0, 0];
  private deployedData: number[] = [0, 0, 0, 0];

  constructor(private rs: RequestService, private rout: Router) { }

  ngOnInit() {
    this.rs.getInfo().subscribe(response => {

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
    if (evt.active[0] != undefined)
      this.rout.navigate([`client-mapped/${evt.active[0]._model.label}`]);
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
    if (evt.active[0] != undefined)
      this.rout.navigate([`skillset/${evt.active[0]._model.label}`]);
  }

  /**
   * @function populateDB
   * @description Populates the database with information from
   *              data script
   */
  populateDB() {
    console.log("HERE");
    this.rs.populateDB().map(response => {
      this.dbMessage = response.data;
      this.myStatus = response.status;
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

      this.options = {
        legend: {
          display: true,
          position: 'right'
        }
      };
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

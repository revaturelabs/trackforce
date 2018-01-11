import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../services/request.service';


import 'rxjs/add/operator/map';
import { Router } from '@angular/router/src/router';

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
  private username: string;

  private currentTime = new Date().getTime();
  private threeMonthsAfter = this.currentTime + 7889238000;
  private threeMonthsBefore = this.currentTime - 7889238000;

  private undeployedLabels = ["Mapped", "Unmapped"];
  private mappedLabels = ['Training', 'Reserved', 'Selected', 'Confirmed'];
  private unmappedLabels = ['Training', 'Open', 'Selected', 'Confirmed'];
  private deployedLabels = ['Mapped', 'Unmapped'];

  private mappedColors = ['#ff8d3f', '#514f4f'];
  private clientTheme = ['#68a225', '#506d2f', '#324851', '#b3de81', '#7da3a1', '#a2c523', '#6e6702', '#2e4600'];
  private skillTheme = ['#004d47', '#00cffa', '#52958b', '#008dcb', '#b2dbd5', '#6eb5c0', '#006c84', '#113743'];

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

  private undeployedData;
  private mappedData;
  private unmappedData;
  private deployedData;

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
      this.undeployedData = [response.data.trainingMapped
        + response.data.reservedMapped
        + response.data.selectedMapped
        + response.data.confirmedMapped,
      response.data.trainingUnmapped
      + response.data.openUnmapped
      + response.data.selectedUnmapped
      + response.data.confirmedUnmapped];

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
      this.mappedData = [response.data.trainingMapped,
      response.data.reservedMapped,
      response.data.selectedMapped,
      response.data.confirmedMapped];

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
      this.unmappedData = [response.data.trainingUnmapped,
      response.data.openUnmapped,
      response.data.selectedUnmapped,
      response.data.confirmedUnmapped];

      /**
       * @member {Array} DeployedData
       * @memberof mainApp.mainCtrl
       * @description DeployedData is an array used to populate the 
       * dataset of the Deployed chart. The dataset contains two numbers:
       * the mapped number is the sum of all mapped associates, the unmapped number
       * is the sum of all unmapped associates. Both numbers contain only deployed associates.
       */
      this.deployedData = [response.data.deployedMapped,
      response.data.deployedUnmapped];
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
        this.rout.navigate([`clientMapped/${evt.active[0]._model.label}`]);
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
    this.rout.navigate([`skillset/${evt.active[0]._model.label}`]);
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
  this.rs.getBatches().subscribe(response => {
    $rootScope.batches = response.data;
  }, function errorCallback(response) {
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
$scope.getCountPerBatchTypeDefault = function () {
  $http({
    method: 'GET',
    url: '/TrackForce/track/batches/' + threeMonthsBefore + '/' + threeMonthsAfter + '/type'
  }).then(function successCallback(response) {
    // this callback will be called asynchronously
    // when the response is available
    $scope.labels = [];
    $scope.data = [];
    var amountType = response.data;
    for (var i = 0; i < amountType.length; i++) {
      $scope.labels.push(amountType[i].curriculum);
      $scope.data.push(amountType[i].value);
    }

    $scope.options = {
      legend: {
        display: true,
        position: 'right'
      }
    };
  }, function errorCallback(response) {
    // called asynchronously if an error occurs
    // or server returns response with an error status.
    $scope.amountType = {
      "JTA_SDET": "2",
      ".NET": "3"
    }
  })
};
});

}

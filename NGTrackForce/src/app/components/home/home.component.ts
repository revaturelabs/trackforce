import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../services/request-service/request.service';
import { DataSyncService } from '../../services/datasync-service/data-sync.service';
import { ChartsModule, Color } from 'ng2-charts';
import { UserService } from '../../services/user-service/user.service';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ClientService } from '../../services/client-service/client.service';
import { BatchService } from '../../services/batch-service/batch.service';
import { Observable } from 'rxjs';


import { Router } from '@angular/router';
import { ThemeConstants } from '../../constants/theme.constants';
import { ChartOptions } from '../../models/ng2-charts-options.model';
import '../../constants/selected-status.constants';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { Associate } from '../../models/associate.model';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  private associates: Associate[];

  loading = true;

  //Message from the back-end
  dbMessage: string;
  myStatus: string;
  username: string;
  labels = [];
  data = [];
  amountType: any;
  count: number[];

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

  // private options = ChartOptions.createOptionsLegend('right');

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
    *@param {Router} router
    * Allows for re-direction to other components
    */
  constructor(
    private rs: RequestService,
    // private ds: DataSyncService,
    private router: Router,
    private as: AssociateService
  ) { }

  ngOnInit() {
    this.getCountForCharts();
  }

  getCountForCharts()
  {
    this.as.getCountAssociates().subscribe(
      count => {
        this.count = count;
        this.undeployedData[0] = this.count['counts'][0];
        this.undeployedData[1] = this.count['counts'][1];
        localStorage.setItem('undeployedData', JSON.stringify(this.undeployedData));
        
        this.deployedData[0] = this.count['counts'][2];
        this.deployedData[1] = this.count['counts'][3];
        localStorage.setItem('deployedData', JSON.stringify(this.deployedData));

        this.unmappedData[0] = this.count['counts'][4];
        this.unmappedData[1] = this.count['counts'][5];
        this.unmappedData[2] = this.count['counts'][6];
        this.unmappedData[3] = this.count['counts'][7];
        localStorage.setItem('unmappedData', JSON.stringify(this.unmappedData));

        this.mappedData[0] = this.count['counts'][8];
        this.mappedData[1] = this.count['counts'][9];
        this.mappedData[2] = this.count['counts'][10];
        this.mappedData[3] = this.count['counts'][11];
        localStorage.setItem('mappedData', JSON.stringify(this.mappedData));

        this.loading = false;
      }
    );
  }

  /**
   * @function MappedOnClick
   * @description When the "Mapped" chart is clicked
   * the global variable selectedStatus is
   * set to the label of the slice
   * clicked.
   */
  mappedOnClick(evt: any) {
    if (evt.active[0] !== undefined) {
      //navigate to client-mapped component
      this.router.navigate([`client-mapped/${evt.active[0]._index}`]);
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
    if (evt.active[0] !== undefined) {
      //navigate to skillset component
      this.router.navigate([`skillset/${evt.active[0]._index}`]);
    }
  }

  deployedOnClick(evt: any) {
    if (evt.active[0] !== undefined) {
      //navigate to skillset component
      this.router.navigate([`deployed/${evt.active[0]._index}`]);
    }
  }

  undeployedOnClick(evt: any) {
    if (evt.active[0] !== undefined) {
      //navigate to skillset component
      this.router.navigate([`undeployed/${evt.active[0]._index}`]);
    }
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

  /////////////////////////////////////////////////////////////
  // THESE FUNCTIONS ARE BEING CALLED SOMEWHERE
  // THEY SHOULD NOT BE CALLED BECUASE THERE IS NO RESOURCE SERVER SIDE
  // FOR THE SERVICES TO ACCESS (NO END POINTS)
  // But we do need to keep them for eventual SalesForce integration
  //
  // /**
  //  * @function populateDB
  //  * @description Populates the database with information from
  //  *              data script
  //  */
  // populateDB() {
  //   this.rs.populateDB().subscribe(response => {
  //     this.load();
  //   }, err => {
  //   });
  // }
  //
  // /**
  //  * @function deleteDB
  //  * @description Truncates all the tables in the database
  //  */
  // deleteDB() {
  //   this.rs.deleteDB().subscribe(response => {
  //     this.load();
  //   }, err => {
  //   })
  // }
  //
  // /**
  //  * @function populateDBSF
  //  * @description SF Populates the database with information
  //  *              from data script
  //  * For Salesforce data integration
  //  */
  // populateDBSF() {
  //   this.rs.populateDBSF().subscribe(response => {
  //     this.load();
  //   }, err => {
  //   });
  // }
  ////////////////////////////////////////////////////////////////

}

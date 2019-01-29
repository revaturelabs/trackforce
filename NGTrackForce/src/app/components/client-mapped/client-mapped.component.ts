import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client-service/client.service';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { ChartsModule, Color } from 'ng2-charts';
import { Router, ActivatedRoute } from '@angular/router';

import { ThemeConstants } from '../../constants/theme.constants';
import { ChartOptions } from '../../models/ng2-charts-options.model';
import '../../constants/selected-status.constants';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { LocalStorage } from '../../constants/local-storage';

@Component({
  selector: 'app-client-mapped',
  templateUrl: './client-mapped.component.html',
  styleUrls: ['./client-mapped.component.css']
})

@AutoUnsubscribe
export class ClientMappedComponent implements OnInit {
  /*
  ============================
  Member variables
  ============================
  */

  /**
   * The types of charts
   */
  public static readonly chartTypes = {
    BAR: 'bar',
    PIE: 'pie',
    POLAR_AREA: 'polarArea'
  };

  /**
   * @description selectedStatus is printed above the displayed chart to indicate
   * the marketing status for the data being displayed.
   */
  public selectedStatus;

  /**
   * @description statusID is initialized with the value stored in the URL.
   * It is used to grab mapped associates from the database based on current
   * marketing status.
   */
  public statusID;

  /**
   * The type of chart
   */
  chartType = ClientMappedComponent.chartTypes.BAR;


  /**
   * @description clientMappedLabels holds the client names fetched from the database
   */
  public clientMappedLabels: string[] = [];

  /**
   *
   * @description clientMappedData holds the number of associate under current status ID
   * fetched from the database
   */
  public clientMappedData: number[] = [];

  /**
   * @description chartLegend is a boolean determining if a legend is displayed.
   * Legends should be displayed for pie and polarArea charts, but not for bar charts
   * (i.e. bar chart legends act differently than the pie and polarArea chart legends)
   */
  public chartLegend: boolean;

  /**
   * @description chartOption contians configuration options for whatever type of chart
   * is being displayed.
   */
  public chartOptions: any;

  /**
   * @description colors used by template for charts.
   * Note: The ThemeConstants.CLIENT_COLORS is currently an array of length 8.
   * For every element of 'data' above a count of 8, the chart color for that data item will be grey.
   */

  mappedLabels = SelectedStatusConstants.MAPPED_LABELS;
  clientColors: Array<Color> = ThemeConstants.CLIENT_COLORS;
  mappedOptions = ChartOptions.createOptionsTitle('Mapped', 24, '#121212', 'right');
  mappedChartType = "pie";
  mappedData: number[] = [0, 0, 0, 0];





  /*
  ============================
  Methods
  ============================
  */
  constructor(
    // private ClientService: ClientService,
    private router: Router,
    private route: ActivatedRoute,
    private associateService: AssociateService
  ) {
    this.chartOptions = {
      xAxes: [{ ticks: { autoSkip: false } }], scales: { yAxes: [{ ticks: { min: 0 } }] },
      legend: {
        display: false
      },
      title: {
        display: true,
        text: this.selectedStatus,
        fontSize: 24,
        fontColor: '#121212'
      },
      responsive: true,
      responsiveAnimationDuration: 1000
    };
  }

  //Run on initialization
  ngOnInit() {
    this.getMappedData();
    //Fetch the statusId from the URL. Used to fetch and display data
     //Adjust the statud id. Values passed in are off by 1.
    this.statusID = Number(this.route.snapshot.paramMap.get('id')) + 1;
    //Initialize the chart to type 'bar'
    this.changeSelectedStatus(this.statusID);

    this.getAssociatesByStatus(this.statusID);

    this.changeChartType('bar');

    //Initialize the title
    this.chartOptions.title.text = this.selectedStatus;
  }

  /**
	 * @function changeChartType
	 * @description Handles changing the chart type when the buttons are clicked.
	 * Removes the chart legend for charts that don't utilize it.
   * @param selectedType string containing the type of chart to display. Should contain 'bar', 'pie', or 'polarArea'
	 */
  public changeChartType(selectedType) {
    this.chartType = selectedType;

    //For 'bar' charts
    if (selectedType === 'bar') {
      this.chartOptions.legend = {
        display: false
      };

      //Add scales to options if it doesn't exist
      if (!this.chartOptions.legend.scales) {
        this.chartOptions.scales = { yAxes: [{ ticks: { min: 0 } }] };
      }
    }
    //For 'pie' or 'polarArea' charts
    else if (selectedType === 'pie' || selectedType === 'polarArea') {
      //Display legend
      this.chartOptions.legend = {
        display: true,
        position: 'right'
      };

      // Remove scales from options, if it exists
      if (this.chartOptions.scales) {
        delete this.chartOptions.scales;
      }
    }
  }

  //Placeholder for events. Current application specifications does not dictate any actions
  public chartClicked(e: any): void {
    this.router.navigate([`associate-listing/client/${this.clientMappedLabels[e.active[0]._index]}/mapped/${this.chartOptions.title.text}`]);
  }

  public chartHovered(e: any): void {
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
      this.router.navigate([`client-mapped/${evt.active[0]._index}`]);
    }
  };

  public getMappedData() {
    this.mappedData = JSON.parse(localStorage.getItem(LocalStorage.MAPPED_DATA_KEY));
  }

  public changeSelectedStatus(statusId: number) {
    if (statusId === 1) {
      this.selectedStatus = "Training";
    } else if (statusId === 2) {
      this.selectedStatus = "Reserved";
    } else if (statusId === 3) {
      this.selectedStatus = "Selected";
    } else if (statusId === 4) {
      this.selectedStatus = "Confirmed";
    }

  }

  public getAssociatesByStatus(statusId: number) {
    // HTTP request to fetch data. See client-service
    this.associateService.getAssociatesByStatus(this.statusID).subscribe(data => {
      /*
      Store the data from the http request in temporary objects.
      In order for the2 property binding refresh on clientMappedData
      and clientMappedLabels to take affect, they need to be set
      equal to an object. (i.e. clientMappedData.push(...)and
      clientMappedLabels.push(...) does not trigger property binding
      and does not display data).
      */
      const temp_clientMappedLabels: string[] = [];
      const temp_clientMappedData: number[] = [];
      this.clientMappedData = temp_clientMappedData;
      this.clientMappedLabels = temp_clientMappedLabels;


      //Loop over 'data' and extract fetched information
      for (const graphCount of data) {
        if (graphCount.count > 0) {
          //Check if the fetched name is empty
          if (graphCount.name === "") {
            temp_clientMappedLabels.push("Client not Specified");
          } else {
            temp_clientMappedLabels.push(graphCount.name);
          }
          temp_clientMappedData.push(graphCount.count);
        }
      }

      //Set data, trigger property binding
      this.clientMappedData = temp_clientMappedData;
      this.clientMappedLabels = temp_clientMappedLabels;
    },
      error => console.error('Error in client-mapped.component.ts getAssociatesByStatus(): ', error.message)
    );

  }

}

import { Component, OnInit } from '@angular/core';
import { ClientMappedService } from '../../services/client-mapped-service/client-mapped-service';
import { ThemeConstants } from '../../constants/theme.constants'; //Used for colors in charts
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { ChartsModule, Color } from 'ng2-charts';
import { Router } from '@angular/router';

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
   * @description chartType determines the type of chart displayed to the user: bar, pie, or polarArea charts
   */
  public chartType: String;

  /**
   * @description clientMappedLabels holds the client names fetched from the database
   */
  public clientMappedLabels: string[];

  /**
   * 
   * @description clientMappedData holds the number of associate under current status ID 
   * fetched from the database
   */
  public clientMappedData: number[];

  /**
   * @description chartLegend is a boolean determining if a legend is displayed. 
   * Legends should be displayed for pie and polarArea charts, but not for bar charts
   * (i.e. bar chart legends act differently than the pie and polarArea chart legends)
   */
  public chartLegend:boolean;

  /**
   * @description chartOption contians configuration options for whatever type of chart
   * is being displayed. 
   */
  public chartOptions:any; 

  /**
   * @description colors used by template for charts.
   * Note: The ThemeConstants.CLIENT_COLORS is currently an array of length 8.
   * For every element of 'data' above a count of 8, the chart color for that data item will be grey.
   */
  private clientTheme: Array<Color> = ThemeConstants.CLIENT_COLORS;

  /* 
  ============================
  Methods
  ============================ 
  */
  constructor(private clientMappedService: ClientMappedService, private rout: Router) {
    this.chartOptions = {
      xAxes:[{ticks:{autoSkip:false}}], scales: {yAxes: [{ticks: {min: 0}}]},
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
    //Fetch the statusId from the URL. Used to fetch and display data 
    //For now, parse out the desired number
    //To-Do: Use "Activated Routes" to fetch the value 
    this.statusID = window.location.href.split('client-mapped/')[1];
    this.statusID = Number(this.statusID) + 1; //Adjust the statud id. Values passed in are off by 1.
    //Initialize 'selectedStatus' to correct string. 
    if(Number(this.statusID) == 1) {
      this.selectedStatus = "Training";
    } else if(Number(this.statusID) == 2) {
      this.selectedStatus = "Reserved";
    } else if(Number(this.statusID) == 3) {
      this.selectedStatus = "Selected";
    } else if(Number(this.statusID) == 4) {
      this.selectedStatus = "Confirmed";
    }
    //Initialize the title
    this.chartOptions.title.text = this.selectedStatus;

    //Initialize the chart to type 'bar'
    this.changeChartType('bar');

    // HTTP request to fetch data. See client-mapped-service 
    this.clientMappedService.getAssociatesByStatus(this.statusID).subscribe( data => {      
      /*
      Store the data from the http request in temporary objects.
      In order for the2 property binding refresh on clientMappedData 
      and clientMappedLabels to take affect, they need to be set 
      equal to an object. (i.e. clientMappedData.push(...)and 
      clientMappedLabels.push(...) does not trigger property binding
      and does not display data).
      */
      let temp_clientMappedLabels: string[] = [];
      let temp_clientMappedData: number[] = [];
      console.log(data);

      //Loop over 'data' and extract fetched information
      for(let d in data) {
        const temp_name = data[d].name;
        const temp_count = data[d].count;
        if(temp_count > 0){
          //Check if the fetched name is empty
          if(data[d].name == ""){
            temp_clientMappedLabels.push("Empty Name");
          } else {
            temp_clientMappedLabels.push(data[d].name);
          }
          temp_clientMappedData.push(data[d].count);
        }
      }

      //Set data, trigger property binding
      this.clientMappedData = temp_clientMappedData;
      this.clientMappedLabels = temp_clientMappedLabels;
    })
  }

  /**
	 * @function changeChartType
	 * @description Handles changing the chart type when the buttons are clicked.
	 * Removes the chart legend for charts that don't utilize it.
   * @param selectedType string containing the type of chart to display. Should contain 'bar', 'pie', or 'polarArea'
	 */
	public changeChartType(selectedType){
    this.chartType = selectedType;

    //For 'bar' charts
    if(selectedType == 'bar') {
      this.chartOptions.legend = { 
        display: false 
      };
      
      //Add scales to options if it doesn't exist
      if(!this.chartOptions.legend.scales) {
        this.chartOptions.scales = {yAxes: [{ticks: {min: 0}}]};
      }
    }
    //For 'pie' or 'polarArea' charts
    else if(selectedType == 'pie' || selectedType == 'polarArea'){
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
  public chartClicked(e:any):void {
    console.log(e);
    this.rout.navigate([`associate-listing/client/${this.clientMappedLabels[e.active[0]._index]}/mapped/${this.chartOptions.title.text}`]);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }
}

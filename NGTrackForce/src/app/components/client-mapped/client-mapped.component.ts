import { Component, OnInit } from '@angular/core';
import { ClientMappedService } from '../../services/client-mapped-service/client-mapped-service';

@Component({
  selector: 'app-client-mapped',
  templateUrl: './client-mapped.component.html',
  styleUrls: ['./client-mapped.component.css']
})
export class ClientMappedComponent implements OnInit {

  /* ===========================
  Member variables
  ============================ */
  /**
   * @description selectedStatus is printed above the displayed chart to indicate 
   * the marketing status for the data being displayed. 
   */
  public selectedStatus; //Pass this in from the service
  
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
   * ??? Check this ???
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

  /* ===========================
  Methods
  ============================ */
  constructor(private clientMappedService: ClientMappedService) {}

  //Run on initialization
  ngOnInit() {
    console.log("Inisde ngOnInit");

    //Initialize the chart to type 'bar'
    this.changeChartType('bar');
    
    console.log("about to make a http");

    //Fetch from the URL the statusId for the data to be displayed 
    //For now, parse out the desired number
    //To-Do: Use "Activated Routes" to fetch the value 
    this.statusID = window.location.href.split('client-mapped/')[1];

    //Initialize 'selectedStatus' to correct string. 
    if(this.statusID == 0) {
      this.selectedStatus = "Training";
    } else if(this.statusID == 1) {
      this.selectedStatus = "Some Status Type";
    } else if(this.statusID == 2) {
      this.selectedStatus = "Some Status Type";
    } else if(this.statusID == 3) {
      this.selectedStatus == "Some Status Type";
    }

    console.log("this.statusID = " + this.statusID);

    //HTTP request to fetch data. See client-mapped-service 
    this.clientMappedService.getAssociatesByStatus(this.statusID).subscribe( data => {
      console.log("made http request");
      
      /*
      Store the data from the http request in temporary objects.
      In order for the property binding refresh on clientMappedData 
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
            console.log('Name is empty');
            temp_clientMappedLabels.push("Empty Name");
          } else {
            temp_clientMappedLabels.push(data[d].name);
          }
          temp_clientMappedData.push(data[d].count);
        }
      }

      //Trigger property binding
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
    console.log("Changing Chart Type!")
    this.chartType = selectedType;

    if(selectedType == 'bar') {
      this.chartLegend = false;
      this.chartOptions = {
        xAxes:[{ticks:{autoSkip:false}}],scales: {yAxes: [{ticks: {min: 0}}]},
      };
    }
    else if(selectedType == 'pie' || selectedType == 'polarArea'){
      this.chartLegend = true;
      this.chartOptions = {
        xAxes:[{ticks:{autoSkip:false}}]
      }
    }
	}


  //Placeholder for events. Current application specifications does not dictate any actions
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }
}

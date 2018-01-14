import { Component, OnInit } from '@angular/core';
import { ClientMappedService } from '../../services/client-mapped-service/client-mapped-service';

@Component({
  selector: 'app-client-mapped',
  templateUrl: './client-mapped.component.html',
  styleUrls: ['./client-mapped.component.css']
})
export class ClientMappedComponent implements OnInit {
  public selectedStatus='Selected'; //Pass this in from the service
  
  public statusID;
  public chartType: String;
  public clientMappedLabels: string[];
  public clientMappedData: number[];
  public clientMappedDataSet: any;
  public chartLegend:boolean;
  public chartOptions:any;

  constructor(private clientMappedService: ClientMappedService) {
    var id=window.location.href.split("form-comp/")[1];
  }

  ngOnInit() {
    console.log("Inisde ngOnInit");

    //Initialize the chart to type 'bar'
    this.changeChartType('bar');
    
    console.log("about to make a http");

    //Fetch the statusId to be displayed from the URL
    //For now, parse out the desired number
    //To-Do: Use "Activated Routes" to fetch the value 
    this.statusID = window.location.href.split('client-mapped/')[1];
    console.log("this.statusID = " + this.statusID);
    this.clientMappedService.getAssociatesByStatus(this.statusID).subscribe( data => {
      console.log("made http request");
      let temp_clientMappedLabels: string[] = [];
      let temp_clientMappedData: number[] = [];
      console.log(data);

      for(let d in data) {
        const temp_name = data[d].name;
        const temp_count = data[d].count;
        if(temp_count > 0){
          if(data[d].name == ""){
            console.log('Name is empty');
            temp_clientMappedLabels.push("Empty Name");
          } else {
            temp_clientMappedLabels.push(data[d].name);
          }
          temp_clientMappedData.push(data[d].count);
        }
      }
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


   // events
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }
}

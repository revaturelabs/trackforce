import { Component, OnInit } from '@angular/core';
import { ClientMappedService } from '../../services/client-mapped-service/client-mapped-service';

@Component({
  selector: 'app-client-mapped',
  templateUrl: './client-mapped.component.html',
  styleUrls: ['./client-mapped.component.css']
})
export class ClientMappedComponent implements OnInit {
  public selectedStatus='Reserved'; //Pass this in from the service
  
  public statusID = 0;
  public chartType: String;
  public clientMappedLabels: string[];
  public clientMappedData: number[];
  public clientMappedDataSet: any;
  public chartLegend:boolean;
  public chartOptions:any;

  

  constructor(
    private clientMappedService: ClientMappedService
  ) { }

  ngOnInit() {
    console.log("Inisde ngOnInit");

    //Initialize the chart to type 'bar'
    this.changeChartType('bar');
    
    this.statusID=0;
		if(this.selectedStatus=='Training'){
			this.statusID=1;
		} else if(this.selectedStatus=='Reserved'){
			this.statusID=2;
		} else if (this.selectedStatus=='Selected'){
			this.statusID=3;
		} else if (this.selectedStatus=='Confirmed'){
			this.statusID=4;
    }
    
     //Hardcoded data, NO http request
     let temp_clientMappedLabels: string[] = [];
     let temp_clientMappedData: number[] = [];
     temp_clientMappedLabels.push("Name 1"); 
     temp_clientMappedLabels.push("Name 2"); 
     temp_clientMappedLabels.push("Name 3");
     temp_clientMappedLabels.push("Name 4");
     temp_clientMappedData.push(100); 
     temp_clientMappedData.push(500);
     temp_clientMappedData.push(300);
     temp_clientMappedData.push(200);
     this.clientMappedData = temp_clientMappedData;
     this.clientMappedLabels = temp_clientMappedLabels;
     this.clientMappedDataSet = [
       {data:temp_clientMappedData}
     ];
    // console.log("about to make a http");
    // this.clientMappedService.getAssociatesByStatus().subscribe( data => {
    //   console.log("made http request");
    //   let temp_clientMappedLabels: string[] = [];
    //   let temp_clientMappedData: number[] = [];

    //   //Hardcoded data
    //   temp_clientMappedLabels.push("Name 1"); 
    //   temp_clientMappedLabels.push("Name 2"); 
    //   temp_clientMappedLabels.push("Name 3");
    //   temp_clientMappedLabels.push("Name 4");
    //   temp_clientMappedData.push(100); 
    //   temp_clientMappedData.push(500);
    //   temp_clientMappedData.push(300);
    //   temp_clientMappedData.push(200);

      // for(let d in data) {
      //   const temp_name = data[d].name;
      //   const temp_count = data[d].count;
      //   if(temp_count > 0){
      //     if(data[d].name == ""){
      //       console.log('Name is empty');
      //       temp_clientMappedLabels.push("Empty Name");
      //     } else {
      //       temp_clientMappedLabels.push(data[d].name);
      //     }
      //     temp_clientMappedData.push(data[d].count);
      //   }
      // }
    //   this.clientMappedData = temp_clientMappedData;
    //   this.clientMappedLabels = temp_clientMappedLabels;
    // })
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

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
  public chartType: String = "pie";
  // private options = {
  //   type : this.chartType, xAxes:[{ticks:{autoSkip:false}}],scales: {yAxes: [{ticks: {min: 0}}]}
  // };
  public clientMappedLabels: string[] = [];
  public clientMappedData: number[] = [];

  constructor(
    private clientMappedService: ClientMappedService
  ) { }

  ngOnInit() {
    console.log("Inisde ngOnInit");

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

    console.log("about to make a http");
    this.clientMappedService.getAssociatesByStatus().subscribe( data => {
      console.log("made http request");
      let temp_clientMappedLabels: string[] = [];
      let temp_clientMappedData: number[] = [];

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


   // events
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }
}

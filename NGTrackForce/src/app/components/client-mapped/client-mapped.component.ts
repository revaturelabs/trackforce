import { Component, OnInit } from '@angular/core';
import { ClientMappedService } from '../../services/client-mapped-service/client-mapped-service';

@Component({
  selector: 'app-client-mapped',
  templateUrl: './client-mapped.component.html',
  styleUrls: ['./client-mapped.component.css']
})
export class ClientMappedComponent implements OnInit {
  private selectedStatus='Training'; //Pass this in from the service
  
  private statusID = 0;
  private chartType: String = "bar";
  private options = {
    type : this.chartType, xAxes:[{ticks:{autoSkip:false}}],scales: {yAxes: [{ticks: {min: 0}}]}
  };
  private clientMappedLabels: String[] = [];
  private clientMappedData: Number[] = [];
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

    this.clientMappedService.getAssociatesByStatus().subscribe( data => {
      for(let d in data) {
        const temp_name = data[d].name;
        const temp_count = data[d].count;
        if(temp_count > 0){
          this.clientMappedLabels.push(data[d].name);
          this.clientMappedData.push(data[d].count);
        }
      }

      console.log(this.clientMappedLabels);
      console.log(this.clientMappedData); 
      console.log(this.statusID);
      console.log(this.selectedStatus);
    })

    
  }
}

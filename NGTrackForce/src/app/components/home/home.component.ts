/**
 * @author Nasir Alauddin, Matthew Snee
 * Nasir - design
 * Matt - implemented services
 */

import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HomeService } from '../../services/home-service/home-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  button: string;
  data: {};

  UndeployedData: {
    trainingMapped: number;
    reservedMapped: number;
    selectedMapped: number;
    confirmedMapped: number;
    trainingUnmapped: number;
    openUnmapped: number;
    selectedUnmapped: number;
    confirmedUnmapped: number;
  };
  UndeployedLabels = [ 'Mapped','Unmapped' ];
  
  MappedData: {
    trainingMapped: number;
    reservedMapped: number;
    selectedMapped: number;
    confirmedMapped: number;
  };
  MappedLabels: [ 'Training','Reserved', 'Selected','Confirmed' ];
  
  UnmappedData: {
    trainingUnmapped: number;
    openUnmapped: number;
    selectedUnmapped: number;
    confirmedUnmapped: number;
  };
  UnmappedLabels = [ 'Training','Open', 'Selected', 'Confirmed' ];

  constructor(private http: HttpClient, private homeService: HomeService) { }

  ngOnInit() {
    this.button = 'Populate Database';
  }

  populateDB(){
    if (this.button === 'Populate Database'){
      this.homeService.populateDB();
      console.log("Populated Database");
      this.button = 'Populate Static SalesForce';
    } else {
      this.homeService.populateDBSF();
      console.log("Populated Database with SaleForce info");
      this.button = 'Populate Database';
    }
    this.setUndeployedData();
    this.setMappedData();
    this.setUnmappedData();
  }

  refresh(){
    this.button = 'Populate Database';
  }

  setUndeployedData(){
    console.log("Getting Undeployed Info");
    this.homeService.getAllData().subscribe(
      (data: any) => {
        console.log(data);
        this.UndeployedData.trainingMapped = data.trainingMapped;
        this.UndeployedData.reservedMapped = data.reservedMapped;
        this.UndeployedData.selectedMapped = data.selectedMapped;
        this.UndeployedData.confirmedMapped = data.confirmedMapped;
        this.UndeployedData.trainingUnmapped = data.trainingUnmapped;
        this.UndeployedData.openUnmapped = data.openUnmapped;
        this.UndeployedData.selectedUnmapped = data.selectedUnmapped;
        this.UndeployedData.confirmedUnmapped = data.confirmedUnmapped;
      }
    )
  }

  setMappedData(){

  }

  setUnmappedData(){

  }


}

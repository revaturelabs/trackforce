import { Component, OnInit } from '@angular/core';
//import {FormsComponent} from '@angular/core';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.css']
})
export class PredictionsComponent implements OnInit {
  private dataReady: boolean = false;
  private startDate: Date = new Date();
  private endDate: Date = new Date();
  private technologies: any[] = [
    {
      name: "Java",
      id: 1,
      selected: false
    },
    {
      name: ".NET",
      id: 2,
      selected: false
    }
  ];
  private expanded: boolean = false;
  private results: any;

  constructor() { }

  ngOnInit() {
    this.getListOfTechnologies();
  }

  toggleCheckboxes() {
    this.expanded = !this.expanded;
  }

  getListOfTechnologies() {
    // this.techService.getTechs().subscribe(
    //   data => {
    //     this.technologies = data;
    //     for (let tech of data) {
    //       this.technologies.push({
    //         name: tech.name,
    //         id: tech.id,
    //         selected: false
    //       })
    //     }
    //   },
    //  err => {
    //    console.log(err);
    //  }
    // );
  }

  getPrediction() {
    // this.techService.getPrediction().subscribe(
    //   data => {
    //     this.results = data;
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // );
    this.results = [
      {
        technology: "Java",
        requested: 5,
        available: 8
      },
      {
        technology: ".NET",
        requested: 6,
        available: 3
      }
    ];
    this.dataReady = true;
  }

}

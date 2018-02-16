import { Component, OnInit } from '@angular/core';
import { TechService} from '../../services/tech-service/tech.service';
//import {FormsComponent} from '@angular/core';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.css']
})
export class PredictionsComponent implements OnInit {
  public dataReady: boolean = false;
  public startDate: Date = new Date();
  public endDate: Date = new Date();
  public numAssociatesNeeded: number;
  public technologies: any[];
  public expanded: boolean = false;
  public results: any;

  constructor(private ts: TechService) { }

  ngOnInit() {
    this.getListOfTechnologies();
  }

  toggleCheckboxes() {
    this.expanded = !this.expanded;
  }

  getListOfTechnologies() {
    this.ts.getAllTechnologies().subscribe(
      data => {
        console.log(data);
        this.technologies = data;
      },
     err => {
       console.log(err);
     }
    );
  }

  getPrediction() {
    console.log(this.technologies);
    let selectedTechnologies = [];
    for (let i=0;i<this.technologies.length;i++) {
      let tech = this.technologies[i];
      if (tech.selected)
        selectedTechnologies.push({
          name: tech.name,
          id: tech.id,
          requested: this.numAssociatesNeeded
        });
    }
    console.log(selectedTechnologies);
    let startTime = new Date(this.startDate).getTime();
    let endTime = new Date(this.endDate).getTime();
    console.log(startTime);
    console.log(endTime);
    if (startTime && endTime && selectedTechnologies.length > 0) {
      this.results = this.ts.getPrediction(startTime,endTime,selectedTechnologies);
      this.dataReady = true;
    }
    //   this.ts.getPrediction(selectedTechnologies).subscribe(
    //     data => {
    //       this.results = data;
    //       this.dataReady = true;
    //     },
    //     err => {
    //       console.log(err);
    //     }
    //   );
  }

}

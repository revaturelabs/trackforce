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
  public message: string = "";

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
      if (tech.selected) selectedTechnologies.push(tech.name);
    }
    console.log(selectedTechnologies);
    let startTime = new Date(this.startDate).getTime();
    let endTime = new Date(this.endDate).getTime();
    console.log(startTime);
    console.log(endTime);
    if (startTime && endTime && selectedTechnologies.length > 0) {
      this.message = "";
      this.ts.getPrediction(startTime,endTime,selectedTechnologies).subscribe(
        data => {
          console.log(data);
          this.results = [];
          let returnedNames = [];
          for (let i=0;i<data.length;i++) {
            let tech = data[i];
            let techName = tech[0];
            let techNumber = tech[1];
            if (selectedTechnologies.includes(techName)) {
              // if techname is in the list of selected technologies, add it as a result
              this.results.push({
                technology: techName,
                requested: this.numAssociatesNeeded,
                available: techNumber
              });
              returnedNames.push(techName);
            }
          }
          for (let i=0;i<selectedTechnologies.length;i++) {
            let selectedTech = selectedTechnologies[i];
            if (!returnedNames.includes(selectedTech)) {
              // if the list of returned technologies does not include one we selected, then it means
              // there are 0 associates with that technology
              this.results.push({
                technology: selectedTech,
                requested: this.numAssociatesNeeded,
                available: 0
              })
            }
          }
          this.dataReady = true;
        },
        err => {
          console.log(err);
        }
      );
    }
  }

}

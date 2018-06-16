import { Component, OnInit } from '@angular/core';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { PredictionService } from '../../services/prediction-service/prediction.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Chart } from 'chart.js';
import { Batch } from '../../models/batch.model';
//import {FormsComponent} from '@angular/core';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.css']
})
@AutoUnsubscribe
export class PredictionsComponent implements OnInit {
  public detailsReady: boolean = false;
  public dataReady: boolean = false;
  public startDate: Date = new Date();
  public endDate: Date = new Date();
  public numAssociatesNeeded: number;
  public technologies: any[];
  public expanded: boolean = false;
  public results: any;
  public message: string = "";
  public batches: Batch[];
  public batchNumberAssociates: number[];

  constructor(private ss: CurriculumService, private ps: PredictionService) { }

  ngOnInit() {
    this.getListofCurricula();
  }

  toggleCheckboxes() {
    this.expanded = !this.expanded;
  }

  getListofCurricula() {
    this.ss.getAllCurricula().subscribe(
      data => {
        let tempArray = [];
        for (let i = 0; i < data.length; i++) {
          let tech = data[i];
          let localtech = {
            id: tech.id,
            name: tech.name,
            selected: false
          }
          tempArray.push(localtech);
        }
        this.technologies = tempArray;
        // IF API RETURNS AN OBJECT INSTEAD OF ARRAY
        // let tempVar = [];
        // for (let key in data) {
        //   let tech = data[key];
        //   tempVar.push(tech);
        // }
        // this.technologies = tempVar;
      },
      err => {
      }
    );
  }

  /**
     * Update the given associate's status/client
     * @param s The start date of the period to be searched
     * @param e The end date of the period to be searched
     */
  getPrediction(s, e) {
    if (s != null) {
      this.startDate = s;
    }
    if (e != null) {
      this.endDate = e;
    }
    let selectedTechnologies = [];
    for (let i = 0; i < this.technologies.length; i++) {
      let tech = this.technologies[i];
      if (tech.selected) selectedTechnologies.push(tech.name);
    }
    let startTime = new Date(this.startDate).getTime();
    let endTime = new Date(this.endDate).getTime();
    if (startTime && endTime && selectedTechnologies.length > 0) {
      this.message = "";
      this.ps.getPrediction(startTime, endTime, selectedTechnologies).subscribe(
        data => {
          this.results = [];
          let returnedNames = [];
          for (let i = 0; i < data.length; i++) {
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
          for (let i = 0; i < selectedTechnologies.length; i++) {
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
          this.message = "There was a problem fetching the requested data!";
        }
      );
    }
  }

  /**
     * Update the given associate's status/client
     * @param s The start date of the period to get the deatils of a technology
     * @param e The end date of the period to to get the deatils of a technology
     * @param event The event object created when the button was clicked to call
     * the function. Contains the id of the button.
     */
  getDetails(s, e, event) {
    if (s != null) {
      this.startDate = s;
    }
    if (e != null) {
      this.endDate = e;
    }

    let startTime = new Date(this.startDate).getTime();
    let endTime = new Date(this.endDate).getTime();
    //Get id of the button that called the function, which should have the name of the technology.
    let tech: string = event.target.id;


    let test = this.ps.getBatchesByCurricula(startTime, endTime, tech).subscribe(data => {
      this.batches = data;
      this.detailsReady = true;
    }, er => {
    });
  }
}

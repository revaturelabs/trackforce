import { BatchService } from './../../services/batch-service/batch.service';
import { Component, OnInit } from '@angular/core';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Batch } from '../../models/batch.model';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model';
import { Curriculum } from '../../models/curriculum.model';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.css']
})
@AutoUnsubscribe
export class PredictionsComponent implements OnInit {

  start:any;
  end:any;
  public detailsReady = false;
  public dataReady = false;
  public startDate: Date = new Date();
  public endDate: Date = new Date();
  public numAssociatesNeeded: number;
  public technologies: any[];
  public expanded = true;
  public results: any;
  public message = "";
  public batches: Object;
  public batchNumberAssociates: number[];
  public associates: Associate[];
  public techNeeded: number[];

  constructor(private ss: CurriculumService, private as: AssociateService, private bs: BatchService) { }

  ngOnInit() {
    this.getListofCurricula();
    this.techNeeded = [];
  }

  toggleCheckboxes() {
    this.expanded = !this.expanded;
  }

  getListofCurricula() {
    this.ss.getAllCurricula().subscribe(
      data => {
        console.log("curricula", data);
        const tempArray = [];
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
    console.log("techNeeded", this.techNeeded);

    this.ss.getAssociateCountByCurriculum(new Date(this.startDate), new Date(this.endDate)).subscribe(
      data => console.log(data),
      err => err
    );
    //****LEGACY CODE****?!?!?!?!?!?!?!?!?!?!?!?
    // this.results = [];
    // this.as.getAllAssociates().subscribe(
    //   data=>{
    //     this.associates=data;


    // if (s != null) {
    //   this.startDate = s;
    // }
    // if (e != null) {
    //   this.endDate = e;
    // }
    // let selectedTechnologies = [];
    // for (let i = 0; i < this.technologies.length; i++) {
    //   let tech = this.technologies[i];
    //   if (tech.selected) {
    //     selectedTechnologies.push(tech.name);
    //   }
    // }
    // let startTime = new Date(this.startDate).getTime();
    // let endTime = new Date(this.endDate).getTime();
    // if (startTime && endTime && selectedTechnologies.length > 0) {
    //   this.message = "";
    //   let returnedNames = [];
    //   for(let t of selectedTechnologies){
    //     let count=0;
    //       for(let associate of this.associates){
    //         if(associate.batch && associate.batch.curriculumName){
    //           if(associate.batch.endDate>=startTime&&associate.batch.endDate<=endTime){
    //             if(associate.batch.curriculumName.name===t){
    //               count++;
    //             }
    //           }
    //         }
    //       }
    //       this.results.push({
    //         technology: t,
    //         requested: this.numAssociatesNeeded,
    //         available: count
    //       });
    //       returnedNames.push(t);
    //     }
    //     this.dataReady=true;
    //   }
    // },
    // err => {
    //   this.message = "There was a problem fetching the requested data!";
    // })
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
    let tech: string = event.target.attributes.id;
    
    // let test = this.ps.getBatchesByCurricula(startTime, endTime, tech).subscribe(
    //   data => {
    //     this.batches = data;
    //     this.detailsReady = true;
    //   },
    //   err => {
    //   });

    /*
      1806_Andrew_H
      This method populates the batches object using a json object.
      The json object is an array with each element containing info on the batch name,
      number of associates in the batch, and the batch's end date.
    */
    this.bs.getBatchDetails( new Date(this.startDate), new Date(this.endDate),tech).subscribe(
      data => {
        this.batches = data;
        console.log(data);
      },
      error => {
        console.log(error);
      });



  }
}

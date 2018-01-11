/** @Author Princewill Ibe **/

import {Component, OnInit} from '@angular/core';
import {Batch} from '../../models/batch.model';
import {BatchService} from '../../services/batch-service/batch.service';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {Router} from '@angular/router';
import {ThemeConstants} from '../../constants/theme.constants';


/**
 * @class BatchListComponent
 * @description This component is the batch list page
 *        to get all batches and show meaningful information
 */
@Component({
  selector: 'app-batch-list',
  templateUrl: './batch-list.component.html',
  styleUrls: ['./batch-list.component.css']
})
export class BatchListComponent implements OnInit {

  pieChartType = 'pie';
  startDate: Date;
  endDate: Date;
  batches: Batch[];
  batchColors: string[] = ThemeConstants.BATCH_COLORS;

  curriculumNames: string[];
  curriculumCounts: number[];


  constructor(private batchService: BatchService, private router: Router, private creds: AuthenticationService) {
  }

  ngOnInit() {
    /*
    if (this.creds.isLoggedIn()) {
      this.router.navigate(['login']);
    }
    */
    //else {
      this.batchService.getAllBatches().subscribe(
        (batches) => {
          console.log(batches);
          this.batches = batches;
          this.updateCountPerCurriculum();
        },
        console.error
      );
    //}
  }


  /**
   * after user selects date range, this handles updating the data,
   * and the corresponding graph accordingly
   */
  public applySelectedRange() {
    this.updateBatches();
    this.updateCountPerCurriculum();
  }

  /**
   * @function updateBatches
   * @memberof BatchListComponent
   * @description This function will return a JavaScript object that contains
   *              all of the batches within startDate and endDate
   */
  public updateBatches() {
    this.batchService.getBatchesByDate(new Date(this.startDate), new Date(this.endDate)).subscribe(
      batches => {
        this.batches = batches.sort((a, b) => {
            if (a.startDate < b.startDate) {
              return -1;
            } else if (a.startDate > b.startDate) {
              return 1;
            }
            return 0;
          }
        );
        console.log('sorted', this.batches);
      },
      console.error
    );
  }

  /**
   * @function updateCountPerCurriculum
   * @memberof BatchListComponent
   * @description This function will return an object that contains
   *              all of the batches within startDate and endDate
   *
   */
  public updateCountPerCurriculum() {
    this.curriculumNames = this.curriculumCounts = null;
    const curriculumCountsMap = new Map<string, number>();
    for (const batch of this.batches) {
      let count = curriculumCountsMap.get(batch.curriculumName);
      if (count === undefined) {
        count = 0;
      }
      curriculumCountsMap.set(batch.curriculumName, count + 1);
    }
    // note: for angular/ng2-charts to recognize the changes to chart data, the object reference has to change
    this.curriculumNames = Array.from(curriculumCountsMap.keys());
    this.curriculumCounts = Array.from(curriculumCountsMap.values());

    console.log(curriculumCountsMap, this.curriculumNames, this.curriculumCounts);
  }


}

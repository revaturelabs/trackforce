/** @Author Princewill Ibe **/

import {Component, OnInit} from '@angular/core';
import {Batch} from '../../models/batch.model';
import {BatchService} from '../../services/batch-service/batch.service';
import {ThemeConstants} from '../../constants/theme.constants';
import {AutoUnsubscribe} from '../../decorators/auto-unsubscribe.decorator';
import {ChartOptions, SideValues} from '../../models/ng2-charts-options.model';
import {Color} from 'ng2-charts';


// TODO: LABELS SHOULD PROPERLY WRAP
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
@AutoUnsubscribe
export class BatchListComponent implements OnInit {

  pieChartType = 'pie';
  startDate: Date;
  endDate: Date;
  batches: Batch[];
  curriculumNames: string[];
  curriculumCounts: number[];
  dataReady = false;
  dataEmpty = false;
  batchColors: Array<Color> = ThemeConstants.BATCH_COLORS;

  chartOptions: ChartOptions = ChartOptions.createOptionsSpacing(
    new SideValues(-100,0, 0, 0),
    new SideValues(0, 0, 0, 0),
    'right', false, false
  );


  constructor(private batchService: BatchService) {
  }

  /**
   * load default batches on initialization
   */
  ngOnInit() {
    const startTime = Date.now();
    this.dataReady = false;
    this.batchService.getDefaultBatches().subscribe(
      (batches) => {
        this.batches = batches;
        this.updateCountPerCurriculum();
        this.dataReady = true;
        const elapsed = Date.now() - startTime;
      },
    );
  }


  /**
   * after user selects date range, this handles updating the data,
   * and the corresponding graph accordingly
   */
  public applySelectedRange() {
    if (this.startDate && this.endDate) {
      this.updateBatches();
    }
  }

  /*
   * reset to original batches
   */
  public resetToDefaultBatches(){
    this.dataReady = false;
    this.batchService.getDefaultBatches().subscribe(
      (batches) => {
        this.batches = batches;
        this.updateCountPerCurriculum();
        this.dataReady = true;
      },
    );
  }

  /**
   * @function updateBatches
   * @memberof BatchListComponent
   * @description This function will return a JavaScript object that contains
   *              all of the batches within startDate and endDate
   */
  public updateBatches() {
    this.dataReady = false;
    this.batchService.getBatchesByDate(new Date(this.startDate), new Date(this.endDate)).subscribe(
      batches => {
        // they serve as flags in template
        this.dataReady = false;
        this.batches = batches.sort((a, b) => {
            if (a.startDate < b.startDate) {
              return -1;
            } else if (a.startDate > b.startDate) {
              return 1;
            }
            return 0;
          }
        );
        this.updateCountPerCurriculum();
        this.dataReady = true;
      },
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

    this.dataEmpty = this.batches.length === 0;

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
  }
}

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BatchService} from '../../services/batch-service/batch.service';
import {Associate} from '../../models/associate.model';
import {AutoUnsubscribe} from '../../decorators/auto-unsubscribe.decorator';

export class BarChartDataSet {
  data: number[];
  label: string;

  constructor(label: string) {
    this.label = label;
    this.data = [];
  }
}

@Component({
  selector: 'app-batch-details',
  templateUrl: './batch-details.component.html',
  styleUrls: ['./batch-details.component.css']
})
@AutoUnsubscribe
export class BatchDetailsComponent implements OnInit {
  chartType = "bar";
  options;
  associates: Associate[];
  dataSets: BarChartDataSet[];
  statusNames: string[];
  isDataReady = false;
  isDataEmpty = false;

  ngOnInit() {
    this.getMapStatusBatch();
  }

  constructor(private route: ActivatedRoute, private batchService: BatchService) {
  }

  /**
   * given batch id, fetches the mapped vs unmapped statistics
   */
  getMapStatusBatch() {
    this.route.params.subscribe(params => {
      const batchId: number = +params['id'];
      console.log(batchId);
      this.isDataReady = false;

      this.batchService.getAssociatesForBatch(batchId)
        .subscribe((data: Associate[]) => {
            this.associates = data;
            console.log('associates', this.associates);

            const statusMap = new Map<string, number>();
            for (const assoc of this.associates) {
              let statusCount = statusMap.get(assoc.marketingStatus);
              if (statusCount === undefined) {
                statusCount = 0;
              }
              statusMap.set(assoc.marketingStatus, statusCount + 1);
            }

            const dataSets: BarChartDataSet[] = [new BarChartDataSet("Mapped"), new BarChartDataSet("Unmapped")];
            for (const label of Array.from(statusMap.keys())) {
              if (label.toLowerCase().indexOf("unmapped") > 0) {
                dataSets[0].data.push(statusMap.get(label));
              }
              else {
                dataSets[1].data.push(statusMap.get(label));
              }
            }
            this.dataSets = dataSets;
            this.statusNames = Array.from(statusMap.keys());
            console.log(this.dataSets);
            this.isDataEmpty = this.associates.length === 0;
            this.isDataReady = true;

            console.log(statusMap);

            this.options = {
              scaleShowVerticalLines: false,
              responsive: true
            };
          },
          console.log
        );
    });
  }
}

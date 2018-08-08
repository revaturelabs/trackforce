import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BatchService } from '../../services/batch-service/batch.service';
import { Associate } from '../../models/associate.model';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { ThemeConstants } from '../../constants/theme.constants';
import { ChartsModule, Color } from 'ng2-charts';
import {Router, NavigationExtras} from "@angular/router";

/**
 * Data relating to Batch details chart.
 */
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
/**
 * Initialize chart details.
 */
@AutoUnsubscribe
export class BatchDetailsComponent implements OnInit {
  chartType = 'bar';
  public options: any = {
    display: true,
    position: 'right',
    scaleShowVerticalLines: false,
    responsive: true,
    legend: { position: 'right' },
    scales: {
      yAxes: [
        {
          id: 'y-axis-1',
          type: 'linear',
          display: true,
          position: 'left',
          ticks: {
            beginAtZero: true
          }
        }
      ]
    },
    tooltips: {
      mode: 'label',
      callbacks: {
        label: function(tooltipItem, data) {
          return (
            data.datasets[tooltipItem.datasetIndex].label +
            ': ' +
            tooltipItem.yLabel
          );
        }
      }
    }
  };
  associates: Associate[];
  dataSets: any[] = [
    { data: [0], label: 'Mapped' },
    { data: [0], label: 'Unmapped' },
    { data: [0], label: 'Other' }
  ];
  statusNames: string[];
  isDataReady = false;
  isDataEmpty = false;
  mappedColors: Array<Color> = ThemeConstants.BATCH_DETAILS_COLORS;

  ngOnInit() {
    this.getMapStatusBatch();
  }

  constructor(
    private route: ActivatedRoute,
    private batchService: BatchService,
    private router: Router
  ) {}

  goToFormComponent(id) {
    this.router.navigate(['/form-comp', id]);
  }



  /**
   * given batch id, fetches the mapped vs unmapped statistics
   */
  getMapStatusBatch() {
    this.route.params.subscribe(params => {
      const batchId: number = +params['id'];
      this.isDataReady = false;

      this.batchService
        .getAssociatesForBatch(batchId)
        .subscribe((data: Associate[]) => {
          this.associates = data;

          //initiialize statuses
          const statusMap = new Map<number, number>();
          statusMap.set(1, 0);
          statusMap.set(2, 0);
          statusMap.set(3, 0);
          statusMap.set(4, 0);
          statusMap.set(5, 0);
          statusMap.set(6, 0);
          statusMap.set(7, 0);
          statusMap.set(8, 0);
          statusMap.set(9, 0);
          statusMap.set(10, 0);
          statusMap.set(11, 0);
          statusMap.set(12, 0);
          for (const assoc of this.associates) {
            let statusCount = statusMap.get(assoc.marketingStatus.id);
            if (statusCount === undefined) {
              statusCount = -1;
            }
            statusMap.set(assoc.marketingStatus.id, statusCount + 1);
          }

          const mappedCount: number =
            statusMap.get(1) +
            statusMap.get(2) +
            statusMap.get(3) +
            statusMap.get(4) +
            statusMap.get(5);
          const unmappedCount: number =
            statusMap.get(6) +
            statusMap.get(7) +
            statusMap.get(8) +
            statusMap.get(9) +
            statusMap.get(10);

          const dataSets: BarChartDataSet[] = [
            new BarChartDataSet('Mapped'),
            new BarChartDataSet('Unmapped'),
            new BarChartDataSet('Other')
          ];

          this.dataSets = [
            {
              data: [mappedCount],
              label: 'Mapped'
            },
            {
              data: [unmappedCount],
              label: 'Unmapped'
            },
            {
              data: [statusMap.get(11) + statusMap.get(12)],
              label: 'Other'
            }
          ];
          this.isDataEmpty = this.associates.length === 0;
          this.isDataReady = true;
          console.log(this.associates);
        });
    });
  }
}

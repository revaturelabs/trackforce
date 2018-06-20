import { Component, OnInit } from '@angular/core';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { ThemeConstants } from '../../constants/theme.constants';
import { Color } from 'ng2-charts';
import { ChartOptions } from '../../models/ng2-charts-options.model';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ChartScale } from '../../models/chart-scale.model';

@Component({
  selector: 'app-undeployed',
  templateUrl: './undeployed.component.html',
  styleUrls: ['./undeployed.component.css']
})
export class UndeployedComponent implements OnInit {

  public static readonly chartTypes = {
    BAR: 'bar',
    PIE: 'pie',
    POLAR_AREA: 'polarArea'
  };

  public selectedStatus;
  public statusID;

  chartType = UndeployedComponent.chartTypes.BAR;

  public clientUndeployedLabels: string[] = [];
  public clientUndeployedData: number[] = [];
  public chartLegend: boolean;
  public chartOptions: any;

  undeployedLabels = SelectedStatusConstants.UNDEPLOYED_LABELS;
  mappedColors: Array<Color> = ThemeConstants.MAPPED_COLORS;
  undeployedChartType = "pie";
  undeployedOptions = ChartOptions.createOptionsTitle('Mapped vs. Unmapped (Not Deployed)', 24, '#121212', 'right');
  undeployedData: number[] = [0, 0];


  constructor(
    private associateService: AssociateService
  ) {
    this.chartOptions = {
      xAxes: [{ ticks: { autoSkip: false } }], scales: { yAxes: [{ ticks: { min: 0 } }] },
      legend: {
        display: false
      },
      title: {
        display: true,
        text: this.selectedStatus,
        fontSize: 24,
        fontColor: '#121212'
      },
      responsive: true,
      responsiveAnimationDuration: 1000
    };
   }

  ngOnInit() {
    this.getUndeployedData();

    this.statusID = window.location.href.split('undeployed/')[1];
    this.statusID = Number(this.statusID) + 1; //Adjust the statud id. Values passed in are off by 1.

    this.changeSelectedStatus(this.statusID);
    this.chartOptions.title.text = this.selectedStatus;



  }

    /**
   * Changes the chart type of this component (does this really need explanation?!)
   */
  changeChartType(type: string) {
    this.chartType = type;
    // changing some chartOptions pre-emptively
    this.chartOptions.type = type;
    switch (type) {
      // if type is either PIE or POLAR_AREA...
      case UndeployedComponent.chartTypes.PIE:
      case UndeployedComponent.chartTypes.POLAR_AREA:
        // ... we're displaying the chart legend and on the right of the container
        this.chartOptions.legend = {
          display: true,
          position: 'right'
        };
        // ... and getting rid of the scales ...
        if (this.chartOptions.scales) { delete this.chartOptions.scales; }
        break;
      // otherwise, for BAR charts...
      case UndeployedComponent.chartTypes.BAR:
        // ...we give no legend...
        this.chartOptions.legend = {
          display: false
        };
        // ...but give scales...
        this.chartOptions.scales = new ChartScale();
        break;
    }
    // it's a mock, for right now
    return type;
  }


  getUndeployedData() {
    this.undeployedData = JSON.parse(localStorage.getItem('undeployedData'));
  }

  changeSelectedStatus(status: number) {
    switch(status){
      case 1: {
        this.selectedStatus = "Mapped";
        break;
      }
      case 2: {
        this.selectedStatus = "Unmapped";
        break;
      }
      default: {
        this.selectedStatus = "Unknown";
        break;
      }
    }
  }



}

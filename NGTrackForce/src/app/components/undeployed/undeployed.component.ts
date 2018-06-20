import { Component, OnInit } from '@angular/core';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { ThemeConstants } from '../../constants/theme.constants';
import { Color } from 'ng2-charts';
import { ChartOptions } from '../../models/ng2-charts-options.model';

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


  constructor() {
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

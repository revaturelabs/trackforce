import { Component, OnInit } from '@angular/core';
import { ChartOptions } from '../../models/ng2-charts-options.model';
import { ThemeConstants } from '../../constants/theme.constants';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { Color } from 'ng2-charts';
import { Router } from '@angular/router';

@Component({
  selector: 'app-deployed',
  templateUrl: './deployed.component.html',
  styleUrls: ['./deployed.component.css']
})
export class DeployedComponent implements OnInit {

  public static readonly chartTypes = {
    BAR: 'bar',
    PIE: 'pie',
    POLAR_AREA: 'polarArea'
  };

  public selectedStatus;
  public statusID;

  chartType = DeployedComponent.chartTypes.BAR;

  public clientDeployedLabels: string[] = [];
  public clientDeployedData: number[] = [];
  public chartLegend: boolean;
  public chartOptions: any;

  deployedLabels = SelectedStatusConstants.DEPLOYED_LABELS;
  mappedColors: Array<Color> = ThemeConstants.MAPPED_COLORS;
  deployedChartType = "pie";
  deployedOptions = ChartOptions.createOptionsTitle('Mapped vs. Unmapped (Deployed)', 24, '#121212', 'right');
  deployedData: number[] = [0, 0];



  constructor(
    private router: Router

  ) { }

  ngOnInit() {
    this.getDeployedData();
    this.statusID = window.location.href.split('client-mapped/')[1];
    this.statusID = Number(this.statusID) + 1; //Adjust the statud id. Values passed in are off by 1.

    this.changeSelectedStatus(this.statusID);
    this.chartOptions.title.text = this.selectedStatus;

    this.changeChartType('bar');

    this.loadChart();
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

  changeChartType(selectedType) {
    this.chartType = selectedType;

    //For 'bar' charts
    if (selectedType === 'bar') {
      this.chartOptions.legend = {
        display: false
      };

      //Add scales to options if it doesn't exist
      if (!this.chartOptions.legend.scales) {
        this.chartOptions.scales = { yAxes: [{ ticks: { min: 0 } }] };
      }
    }
    //For 'pie' or 'polarArea' charts
    else if (selectedType === 'pie' || selectedType === 'polarArea') {
      //Display legend
      this.chartOptions.legend = {
        display: true,
        position: 'right'
      };

      // Remove scales from options, if it exists
      if (this.chartOptions.scales) {
        delete this.chartOptions.scales;
      }
    }
  }


  loadChart() {

  }

  getDeployedData() {
    this.deployedData = JSON.parse(localStorage.getItem('deployedData'));
  }

}

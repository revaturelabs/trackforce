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


  constructor() { }

  ngOnInit() {
    
  }

}

import { Component, OnInit } from '@angular/core';
import { ChartOptions } from '../../models/ng2-charts-options.model';
import { ThemeConstants } from '../../constants/theme.constants';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { Color } from 'ng2-charts';
import { Router } from '@angular/router';
import { AssociateService } from '../../services/associate-service/associate.service';

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
    private router: Router,
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
    this.getDeployedData();
    this.statusID = window.location.href.split('deployed/')[1];
    this.statusID = Number(this.statusID) + 1; //Adjust the statud id. Values passed in are off by 1.

    this.changeSelectedStatus(this.statusID);
    this.chartOptions.title.text = this.selectedStatus;

    this.changeChartType('bar');

    this.loadChart();
  }

  changeSelectedStatus(status: number) {
    console.log(status);
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
    this.associateService.getAssociatesByStatus(6).subscribe(data => {
      /*
      Store the data from the http request in temporary objects.
      In order for the2 property binding refresh on clientMappedData
      and clientMappedLabels to take affect, they need to be set
      equal to an object. (i.e. clientMappedData.push(...)and
      clientMappedLabels.push(...) does not trigger property binding
      and does not display data).
      */
      const temp_clientDeployedLabels: string[] = [];
      const temp_clientDeployedData: number[] = [];
      this.clientDeployedData = temp_clientDeployedData;
      this.clientDeployedLabels = temp_clientDeployedLabels;

      console.log(data);

      //Loop over 'data' and extract fetched information
      for (const graphCount of data) {
        if (graphCount.count > 0) {
          //Check if the fetched name is empty
          if (graphCount.name === "") {
            temp_clientDeployedLabels.push("Client not Specified");
          } else {
            temp_clientDeployedLabels.push(graphCount.name);
          }
          temp_clientDeployedData.push(graphCount.count);
        }
      }
      console.log(temp_clientDeployedData);
      console.log(temp_clientDeployedLabels);

      //Set data, trigger property binding
      this.clientDeployedData = temp_clientDeployedData;
      this.clientDeployedLabels = temp_clientDeployedLabels;
    });

  }

  deployedOnClick(evt: any) {
    if (evt.active[0] !== undefined) {
      //navigate to skillset component
      this.router.navigate([`deployed/${evt.active[0]._index}`]);
      window.location.reload();
    }
  }

  public chartClicked(e: any): void {
    this.router.navigate([`associate-listing/client/${this.clientDeployedLabels[e.active[0]._index]}/mapped/Deployed`]);
  }

  getDeployedData() {
    this.deployedData = JSON.parse(localStorage.getItem('deployedData'));
  }

}

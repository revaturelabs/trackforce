import { Component, OnInit } from '@angular/core';
import { ChartOptions } from '../../models/ng2-charts-options.model';
import { ThemeConstants } from '../../constants/theme.constants';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { Color } from 'ng2-charts';
import { Router, ActivatedRoute } from '@angular/router';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ChartScale } from '../../models/chart-scale.model';

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

  public selectedStatus: string;
  public statusID;
  public marketingStatusId: number;

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
    private route: ActivatedRoute,
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
     //Adjust the statud id. Values passed in are off by 1.
    this.statusID = Number(this.route.snapshot.paramMap.get('id')) + 1;

    this.changeSelectedStatus(this.statusID);
    this.chartOptions.title.text = this.selectedStatus;

    this.changeChartType('bar');

    this.loadChart();
  }

  changeSelectedStatus(status: number) {
    switch(status){
      case 1: {
        this.selectedStatus = "Mapped";
        this.marketingStatusId = 5;
        break;
      }
      case 2: {
        this.selectedStatus = "Unmapped";
        this.marketingStatusId = 10;
        break;
      }
      default: {
        this.selectedStatus = "Unknown";
        break;
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

      //Set data, trigger property binding
      this.clientDeployedData = temp_clientDeployedData;
      this.clientDeployedLabels = temp_clientDeployedLabels;
    });

  }

  deployedOnClick(evt: any) {
    if (evt.active[0] !== undefined) {
      //navigate to skillset component
      this.router.navigate([`deployed/${evt.active[0]._index}`]);
    }
  }

  public chartClicked(e: any): void {
    this.router.navigate([`associate-listing/client/${this.clientDeployedLabels[e.active[0]._index]}/mapped/Deployed`]);
  }

  getDeployedData() {
    this.deployedData = JSON.parse(localStorage.getItem('deployedData'));
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
      case DeployedComponent.chartTypes.PIE:
      case DeployedComponent.chartTypes.POLAR_AREA:
        // ... we're displaying the chart legend and on the right of the container
        this.chartOptions.legend = {
          display: true,
          position: 'right'
        };
        // ... and getting rid of the scales ...
        if (this.chartOptions.scales) { delete this.chartOptions.scales; }
        break;
      // otherwise, for BAR charts...
      case DeployedComponent.chartTypes.BAR:
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


}

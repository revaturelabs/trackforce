import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RequestService } from '../../services/request-service/request.service';
import { ClientService } from '../../services/client-service/client.service';
// import { Subject ,  Observable } from 'rxjs';
import { Client } from '../../models/client.model';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { ThemeConstants } from '../../constants/theme.constants';
import { Color } from 'ng2-charts';
import { StatusInfo } from '../../models/status-info.model'
//import { SearchFilterPipe } from '../../pipes/search-filter/search-filter.pipe';

/**
 * @author Han Jung
 * @description Translation of clientCtrl.js
 */
@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  public showNoData = false;
  public loading = true;
  public placeholder = 'Loading client list . . .'
  public selectedCompany: string;
  public clientInfo: Client[];
  public mappedClientInfo: Client[];
  public clientNames: string[] = [];
  public searchName;
  // chart variable
  public barChartLabel: string[] = SelectedStatusConstants.CLIENT_LABELS;
  public barChartType = 'bar';
  public barChartLegend = false;
  public barChartColors: Array<Color> = ThemeConstants.BAR_COLORS

  public barChartOptions: any = {
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
        label: function (tooltipItem, data) {
          return data.datasets[tooltipItem.datasetIndex].label + ": " + tooltipItem.yLabel;
        }
      }
    }
  };
  // data values initialize to 1 for animation
  public barChartData: any[] = [{ data: [0, 0, 0, 0], label: 'Mapped' }];


  constructor(
    private rs: RequestService,
    private clientService: ClientService
  ) {
  }

  ngOnInit() {
    //this.getFiftyClients();
    this.getAllClients();
    this.initChartData();
  }


  // get client names from data and push to clientNames string array
  getAllClients() {

    this.clientService.getAllClients().subscribe(
      clients => {
        if (Array.isArray(clients) && clients.length === 0) {
          return;
        }
        // save array of object Client
        this.clientInfo = clients;
        this.loading = false;
        // clear name list to reload list and run through filter
        this.clientNames.length = 0;
        for(const client of clients){
          this.clientNames.push(client.name);
        }
      }, err => {
        console.error("Failed grabbing names");
      });
  }

  getFiftyClients(){
    this.clientService.getFiftyClients().subscribe(
      clients => {
        this.clientInfo = clients;

        for(const client of clients){
          this.clientNames.push(client.name);
        }
        this.loading = false;
        this.placeholder = 'Enter a client . . .';
      },
      err => {
        console.error("Failed grabbing names");
      }
    )
  }

  //This method was meant to return all clients with mapped associates.
  //But is currently not used due to incorrect query in the back-end.
  getMappedClients(){
    this.clientService.getAllClientsWithAssoc().subscribe(
      clients => {
        this.mappedClientInfo = clients;
        this.clientNames.length = 0;

        for(const client of clients){
          this.clientNames.push(client.name);
        }
      },     
        error => console.error('Error in client-list.component.ts getMappedClients(): ', error.message)
      )
  }

  //show data for all clients
  initChartData() {
    this.selectedCompany = "All Client Data";

    const stat = new StatusInfo;
    this.searchName = '';
    this.clientService.getClientCount(-1).subscribe(
      count => {
        stat.trainingMapped = count[0];
        stat.reservedMapped = count[1];
        stat.selectedMapped = count[2];
        stat.confirmedMapped = count[3];

        this.barChartData = [
          {
            data: [stat.trainingMapped,
              stat.reservedMapped,
              stat.selectedMapped,
              stat.confirmedMapped],
            label: 'Mapped'
          }
        ]
      },
      err => {
        console.error("Error in client-list.component.ts initChartData(): ", err.message);
      }
    );
  }

  // get client name and find id to request client information
  getOneClient(name: string) {
    this.selectedCompany = name;
    const oneClient = this.clientInfo.find(item => item['name'] === name);

    const stat = new StatusInfo;
    this.clientService.getClientCount(oneClient.id).subscribe(
      count => {
        stat.trainingMapped = count[0];
        stat.reservedMapped = count[1];
        stat.selectedMapped = count[2];
        stat.confirmedMapped = count[3];
        oneClient.stats = stat;

        this.barChartData = [
          {
            data: [oneClient.stats.trainingMapped,
              oneClient.stats.reservedMapped,
              oneClient.stats.selectedMapped,
              oneClient.stats.confirmedMapped],
            label: 'Mapped'
          }
        ]
      },
      err => {
        console.error('Error in client-list.component.ts getOneClient(): ', err.message);
      }
    );
  }
}

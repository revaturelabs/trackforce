import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RequestService } from '../../services/request-service/request.service';
import { Subject } from 'rxjs/Subject';
import { Client } from '../../models/client.model';
import { Observable } from 'rxjs/Observable';
import { SelectedStatusConstants } from '../../constants/selected-status.constants';
import { ThemeConstants } from '../../constants/theme.constants';
import { Color } from 'ng2-charts';

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
  public selectedCompany: string;
  public clientInfo: Client[];
  public clientNames: string[] = [];
  public client$: any;
  public searchName;
  // chart variable
  public barChartLabel: string[] = SelectedStatusConstants.CLIENT_LABELS;
  public barChartType: string = 'bar';
  public barChartLegend: boolean = true;
  public barChartColors: Array<Color> = ThemeConstants.BAR_COLORS

  public barChartOptions: any = {
    display: true,
    position: 'right',
    scaleShowVerticalLines: false,
    responsive: true,
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
        },
      }
    },
  }
  // data values initialize to 1 for animation
  public barChartData: any[] = [{ data: [0, 0, 0, 0], label: 'Mapped' }, { data: [0, 0, 0, 0], label: 'Unmapped' }];


  constructor(
    private rs: RequestService) {
  }

  ngOnInit() {
    this.getAllClients()
    this.getTotalChartData();
  }

  // get client names from data and push to clientNames string array
  getAllClients() {
    var self = this;
    this.rs.getClients()
      .subscribe(
      clientNames => {
        console.log(clientNames);
        // save array of object Client
        this.clientInfo = clientNames;
        // clear name list to reload list and run through filter
        this.clientNames.length = 0;
        // push list of names to an array
        for (let client of clientNames) {

          // Hide clients who do not have associates
          let stats = client.stats;
          if (stats.trainingMapped > 0 || stats.trainingUnmapped > 0 ||
            stats.reservedMapped > 0 || stats.openUnmapped > 0 || 
          stats.selectedMapped > 0 || stats.selectedUnmapped > 0 ||
          stats.confirmedMapped > 0 || stats.confirmedUnmapped > 0)
            this.clientNames.push(client.tfClientName);
        }
      }, err => {
        console.log("Failed grabbing names");
      });
  }

  getTotalChartData() {
    this.rs.getTotals()
      .subscribe(
      // asign response to this.clients
      client => {
        console.log();
        this.client$ = client;
        this.selectedCompany = this.client$.name;
        this.barChartData = [
          {
            data: [this.client$.trainingMapped, this.client$.reservedMapped, this.client$.selectedMapped, this.client$.confirmedMapped],
            label: 'Mapped',
          },
          {
            data: [this.client$.trainingUnmapped, this.client$.openUnmapped, this.client$.selectedUnmapped, this.client$.confirmedUnmapped],
            label: 'Unmapped',
          }
        ]
        console.log(this.barChartData);
      }, err => {
        console.log("Failed grabbing clients");
      });
  }

  // get client name and find id to request client information
  getOneClient(name: string) {
    this.selectedCompany = name;
    let oneClient = this.clientInfo.find(item => item['tfClientName'] == name);
    this.rs.getOneClient(oneClient.id)
      .subscribe(
      client => {
        this.client$ = client;
        this.barChartData = [
          {
            data: [this.client$.trainingMapped, this.client$.reservedMapped, this.client$.selectedMapped, this.client$.confirmedMapped],
            label: 'Mapped',
          },
          {
            data: [this.client$.trainingUnmapped, this.client$.openUnmapped, this.client$.selectedUnmapped, this.client$.confirmedUnmapped],
            label: 'Unmapped',
          }
        ]
        console.log(this.barChartData);
      }, err => {
        console.log("Failed grabbing client");
      });
  }
}

import { AsyncSubject, BehaviorSubject } from 'rxjs';
import { AssociateService } from './../../services/associate-service/associate.service';
import { Associate } from './../../models/associate.model';
import { Client } from './../../models/client.model';
import { ClientService } from './../../services/client-service/client.service';
import { SelectedStatusConstants } from './../../constants/selected-status.constants';
import { Component, OnInit, OnDestroy, AfterViewInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router, NavigationExtras } from '@angular/router';
import { Helpers } from '../../lsHelper';
import { Trainer } from '../../models/trainer.model';


export interface DialogData {
  selected: Set<number>;
  clients: Client[];
  associates: Set<Associate>;
  statuses: String[];
}

@Component({
  selector: 'app-associate-list-page',
  templateUrl: './associate-list-page.component.html',
  styleUrls: ['./associate-list-page.component.css']
})
export class AssociateListPageComponent implements OnInit, OnDestroy, AfterViewInit {

  public readonly associateStatuses: String[] = [];
  public clientList$;
  public scrollEvent$;
  public scrollingTable;
  public showModal = false;

  public filterByStatus = "";
  public filterByClient = "";
  public filterByText = "";

  public associates$: BehaviorSubject<Associate[]>;
  public listOfAssociates: Associate[] = [];
  public isFetching = true;
  public currentAssociatesSelected: Set<number> = new Set<number>();

  public statusToId = {
    "MAPPED: TRAINING": 1,
    "MAPPED: RESERVED": 2,
    "MAPPED: SELECTED": 3,
    "MAPPED: CONFIRMED": 4,
    "MAPPED: DEPLOYED": 5,
    "UNMAPPED: TRAINING": 6,
    "UNMAPPED: OPEN": 7,
    "UNMAPPED: SELECTED": 8,
    "UNMAPPED: CONFIRMED": 9,
    "UNMAPPED: DEPLOYED": 10,
    "DIRECTLY PLACED": 11,
    "TERMINATED": 12
  }

  constructor(private clientService: ClientService, public associateService: AssociateService, public dialog: MatDialog, public lsHelp: Helpers) { }

  ngOnInit() {
    //Local storage items generated on page load must be destroyed onInit and OnDestroy to avoid an infinite loop on refresh or return
   this.lsHelp.removeStorageItem("clientGetAll");
   this.lsHelp.removeStorageItem("checked");
   this.lsHelp.removeStorageItem('associatePage|/page?startIndex=0&numResults=500');
    /**
     * This is weird you are correct.
     *
     * There are five sets of arrays in the constants that hold the all the potential
     * statuses of an associate. To avoid having these constants in many places they
     * are only mentioned in the one file. However, there is no way to set all of them
     * except for the way you see bellow (or explicitly hard coding). Therefore, this
     * method will be used for now but should be cleaned up in the constants. Only reason
     * for not changing now is they are in that structure for the graphs so someone will
     * need to look into that dependency before just changing them.
     *
     * ? I have no idea what the statuses mean and which ones should actually be used will find out tuesday
     */

    // Add option for none

    const possibleTrainer = JSON.parse(this.lsHelp.localStorageItem("currentUser"));
    if (possibleTrainer.role === 2){
      this.associateStatuses.push("");
      for (const status of SelectedStatusConstants.MAPPED_LABELS) {
        this.associateStatuses.push(`Mapped: ${status}`);
      }
      for (const status of SelectedStatusConstants.UNMAPPED_LABELS) {
        this.associateStatuses.push(`Unmapped: ${status}`);
      }
      this.associateStatuses.push(SelectedStatusConstants.DIRECTLY_PLACED);
      this.associateStatuses.push(SelectedStatusConstants.TERMINATED);
  
      // Grab Clients (for now this is messy needs to be handled else ware)
      this.clientList$ = this.clientService.getAllClients();
      this.associates$ = this.associateService.fetchAssociateSnapshotT(60, {});
  
      this.associates$.subscribe((data: Associate[]) => {
        if (Array.isArray(data) && data.length !== 0) {
          this.isFetching = false;
          this.listOfAssociates = this.listOfAssociates.concat(data);
        }
      },
        error => console.error('Error in associate-list-page.component.ts ngOnInit(): ', error.message)
      );
      // Grab Clients (for now this is messy needs to be handled else ware)

    }
    else{
    this.associateStatuses.push("");
    for (const status of SelectedStatusConstants.MAPPED_LABELS) {
      this.associateStatuses.push(`Mapped: ${status}`);
    }
    for (const status of SelectedStatusConstants.UNMAPPED_LABELS) {
      this.associateStatuses.push(`Unmapped: ${status}`);
    }
    this.associateStatuses.push(SelectedStatusConstants.DIRECTLY_PLACED);
    this.associateStatuses.push(SelectedStatusConstants.TERMINATED);  

    // Grab Clients (for now this is messy needs to be handled else ware)
    this.clientList$ = this.clientService.getAllClients();
    this.associates$ = this.associateService.fetchAssociateSnapshot(60, {});

    this.associates$.subscribe((data: Associate[]) => {
      if (Array.isArray(data) && data.length !== 0) {
        this.isFetching = false;
        this.listOfAssociates = this.listOfAssociates.concat(data);
        this.orderByLastName();
      }
    },
      error => console.error('Error in associate-list-page.component.ts ngOnInit(): ', error.message)
    );
  }
    this.lsHelp.localStorageSet("checked",JSON.stringify(this.listOfAssociates));
  }  

  ngAfterViewInit() {
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
    this.scrollingTable = document.getElementById('tableScroller');
    this.scrollingTable.addEventListener('scroll', this.onScroll.bind(this));
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
   this.lsHelp.removeStorageItem("checked");
   this.scrollingTable.removeEventListener('scroll', this.onScroll.bind(this));
   this.lsHelp.removeStorageItem("clientGetAll");
   this.lsHelp.removeStorageItem('associatePage|/page?startIndex=0&numResults=500');
   let trainNerd: Trainer;
   trainNerd = JSON.parse(this.lsHelp.localStorageItem("currentTrainer"));
   this.lsHelp.removeStorageItem(`associatePage|/pagetrain?startIndex=0&numResults=60&trainerId=${trainNerd.id}`);
  }

  onScroll(event: Event) {
    if (this.scrollingTable.scrollHeight - this.scrollingTable.scrollTop + this.scrollingTable.clientHeight <= 5000) {
      this.getNextPage();
    }
  }

  orderByBatchName(){
    this.listOfAssociates.sort((a,b) => 
      (a.batch.batchName).localeCompare((b.batch.batchName))
    );
  }
  orderByClient(){
    this.listOfAssociates.sort((a,b) => 
      ((a.client) ? a.client.name : '').localeCompare((b.client) ? b.client.name : '')
    );
  }
  orderByMarketStatus(){
    this.listOfAssociates.sort((a,b) => 
     (a.marketingStatus.name).localeCompare((b.marketingStatus.name))
    );
  }
  orderByVerification(){  
    this.listOfAssociates.sort((a,b) => 
      (a.user.isApproved.toString()).localeCompare((b.user.isApproved.toString()))
    ); 
  }
  orderByLastName(){
    this.listOfAssociates.sort((a,b) => 
     (a.lastName+a.firstName).localeCompare((b.lastName+a.firstName))
    );
  }
  orderByFirstName(){
    this.listOfAssociates.sort((a,b) => 
     (a.firstName+a.lastName).localeCompare((b.firstName+a.lastName))
    );
  }

  submitFilter(e) {
    const filter = {};

    if (this.filterByClient) {
      filter["client"] = this.filterByClient;
    }

    if (this.filterByStatus) {
      filter["status"] = this.statusToId[this.filterByStatus.toUpperCase()];
    }

    if (this.filterByText) {
      let name = this.filterByText.split(" ");
      // first name AND last name entered
      if(name.length > 1) {
        filter["firstName"] = name[0];
        filter["lastName"] = name[1];
      }
      // first name OR last name entered 
      else {
        filter["sortText"] = this.filterByText;
      }
    }

    console.log("In submit filter method");
    this.isFetching = true;
    this.associateService.fetchAssociateSnapshot(60, filter);
    this.listOfAssociates = [];
  }

  clearFilter(): void {
    this.filterByStatus = "";
    this.filterByClient = "";
    this.filterByText = "";
  }

  stopProp(e) {
    e.stopPropagation();
  }

  getAssociateDetails(click: Event, associate: Associate): void {
    if (!this.currentAssociatesSelected.has(associate.id)) {
    //  click.currentTarget.className += "selected";
      this.currentAssociatesSelected.add(associate.id);
    } else {
      this.currentAssociatesSelected.delete(associate.id);
    }
  }

  getNextPage() {
    const potentialTrainer = JSON.parse(this.lsHelp.localStorageItem("currentUser"));
    if (potentialTrainer.role === 2 && !this.isFetching){  
      this.isFetching = true;
      this.associateService.fetchNextSnapshotT();
    }
    else  {
      if (!this.isFetching){
        this.isFetching = true;
        this.associateService.fetchNextSnapshot();
      }
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(UpdateDialogComponent, {
      width: '250px',
      data: {selected: this.currentAssociatesSelected, clients: this.clientList$.value, associates: this.currentAssociatesSelected, statuses: this.associateStatuses}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.currentAssociatesSelected.clear();
      this.listOfAssociates = [];
      this.submitFilter(null);
    },
      error => console.error('Error in associate-list-page.component.ts ngOnInit(): ', error.message)
    );
  }

  navigate(event, id) {
    event.stopPropagation();
  }
}

@Component({
  selector: 'app-update-dialog-component',
  templateUrl: 'update-dialog-component.html',
  styleUrls: ['./associate-list-page.component.css']
})
export class UpdateDialogComponent {

  public verification = 0;
  public client = -1;
  public status = "";

  public statusToId = {
    "MAPPED: TRAINING": 1,
    "MAPPED: RESERVED": 2,
    "MAPPED: SELECTED": 3,
    "MAPPED: CONFIRMED": 4,
    "MAPPED: DEPLOYED": 5,
    "UNMAPPED: TRAINING": 6,
    "UNMAPPED: OPEN": 7,
    "UNMAPPED: SELECTED": 8,
    "UNMAPPED: CONFIRMED": 9,
    "UNMAPPED: DEPLOYED": 10,
    "DIRECTLY PLACED": 11,
    "TERMINATED": 12
  }

  constructor(private associateService: AssociateService,
    public dialogRef: MatDialogRef<UpdateDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  submit() {
    this.associateService
      .updateAssociates(
        Array.from(this.data.selected),
        Number(this.verification),
        (this.status !== "") ? this.statusToId[this.status.toUpperCase()] : "",
        Number(this.client)
      ).then(() => this.dialogRef.close())
      .catch((err) => console.error(err));
  }
}

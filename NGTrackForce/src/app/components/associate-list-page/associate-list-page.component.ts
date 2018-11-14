import { AsyncSubject, BehaviorSubject } from 'rxjs';
import { AssociateService } from './../../services/associate-service/associate.service';
import { Associate } from './../../models/associate.model';
import { Client } from './../../models/client.model';
import { ClientService } from './../../services/client-service/client.service';
import { SelectedStatusConstants } from './../../constants/selected-status.constants';
import { Component, OnInit, OnDestroy, AfterViewInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router, NavigationExtras } from '@angular/router';


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

  constructor(private clientService: ClientService, public associateService: AssociateService, public dialog: MatDialog) { }

  ngOnInit() {
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
      }
    });
  }


  

  ngAfterViewInit() {
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
    this.scrollingTable = document.getElementById('tableScroller');
    this.scrollingTable.addEventListener('scroll', this.onScroll.bind(this));
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
   this.scrollingTable.removeEventListener('scroll', this.onScroll.bind(this));
  }

  onScroll(event: Event) {
    if (this.scrollingTable.scrollHeight - this.scrollingTable.scrollTop + this.scrollingTable.clientHeight <= 5000) {
      this.getNextPage();
    }
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
      filter["sortText"] = this.filterByText;
    }

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
    if (!this.isFetching) {
      this.isFetching = true;
      this.associateService.fetchNextSnapshot();
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
    });
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
  public client = 0;
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

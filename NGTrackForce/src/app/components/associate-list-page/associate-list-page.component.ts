import { AsyncSubject, BehaviorSubject } from 'rxjs';
import { AssociateService } from './../../services/associate-service/associate.service';
import { Associate } from './../../models/associate.model';
import { Client } from './../../models/client.model';
import { ClientService } from './../../services/client-service/client.service';
import { SelectedStatusConstants } from './../../constants/selected-status.constants';
import { Component, OnInit, OnDestroy, AfterViewInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface DialogData {
  animal: string;
  name: string;
  associates: Set<Associate>;
}

@Component({
  selector: 'app-associate-list-page',
  templateUrl: './associate-list-page.component.html',
  styleUrls: ['./associate-list-page.component.css']
})
export class AssociateListPageComponent implements OnInit, OnDestroy, AfterViewInit {

  protected readonly associateStatuses: string[] = [];
  protected clientList$;
  protected scrollEvent$;
  protected scrollingTable;
  protected showModal = false;

  protected filterByStatus = "";
  protected filterByClient = "";

  protected associates$: BehaviorSubject<Associate[]>;
  protected listOfAssociates: Associate[] = [];
  private isFetching = true;
  protected currentAssociatesSelected: Set<number> = new Set<number>();

  constructor(private clientService: ClientService, private associateService: AssociateService, public dialog: MatDialog) { }

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
      this.isFetching = false;
      if (Array.isArray(data) && data.length !== 0) {
        this.listOfAssociates = this.listOfAssociates.concat(data);
      } else {
        console.log('Done with Data')
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
    
    // console.log(this.scrollingTable.scrollHeight)
    // console.log(this.scrollingTable.scrollTop + this.scrollingTable.clientHeight)
    

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
      filter["status"] = this.associateStatuses.findIndex((value) => value === this.filterByStatus)
    }

    this.associateService.fetchAssociateSnapshot(20, filter);
    this.listOfAssociates = [];
  }

  clearFilter(): void {
    this.filterByStatus = "";
    this.filterByClient = "";
  }

  getAssociateDetails(click: Event, associate: Associate): void {
    if (!this.currentAssociatesSelected.has(associate.id)) {
    //  click.currentTarget.className += "selected";
      this.currentAssociatesSelected.add(associate.id);
    } else {
      this.currentAssociatesSelected.delete(associate.id);
    }
    console.log(this.currentAssociatesSelected);
    
  }

  getNextPage() {
    if (!this.isFetching) {
      console.log("FIRE");
      this.isFetching = true;
      this.associateService.fetchNextSnapshot();
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(UpdateDialogComponent, {
      width: '250px',
      data: {name: 'it me', animal: 'panda', associates: this.currentAssociatesSelected}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }
}

@Component({
  selector: 'app-update-dialog-component',
  templateUrl: 'update-dialog-component.html',
})
export class UpdateDialogComponent {
 
  constructor(
    public dialogRef: MatDialogRef<UpdateDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {console.log(data)}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
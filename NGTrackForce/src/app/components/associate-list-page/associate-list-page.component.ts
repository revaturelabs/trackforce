import { AsyncSubject, BehaviorSubject } from 'rxjs';
import { AssociateService } from './../../services/associate-service/associate.service';
import { Associate } from './../../models/associate.model';
import { Client } from './../../models/client.model';
import { ClientService } from './../../services/client-service/client.service';
import { SelectedStatusConstants } from './../../constants/selected-status.constants';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-associate-list-page',
  templateUrl: './associate-list-page.component.html',
  styleUrls: ['./associate-list-page.component.css']
})
export class AssociateListPageComponent implements OnInit, OnDestroy {

  protected readonly associateStatuses: string[] = [];
  protected clientList$;
  protected scrollEvent$;

  protected filterByStatus = "";
  protected filterByClient = "";

  protected associates$: BehaviorSubject<Associate[]>;
  protected listOfAssociates: Associate[] = [];
  private isFetching = true;
  protected currentAssociatesSelected: Set<number> = new Set<number>();

  constructor(private clientService: ClientService, private associateService: AssociateService) { }

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
        console.log(data)
      }
    });

    window.addEventListener('scroll', this.onScroll.bind(this));
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    window.removeEventListener('scroll', this.onScroll);
  }

  onScroll(event: Event) {
    console.log(event)

    if (document.body.scrollHeight - window.scrollY + window.screen.height <= 5000) {
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
}

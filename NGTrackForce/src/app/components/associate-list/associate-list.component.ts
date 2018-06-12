import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model';
import { RequestService } from '../../services/request-service/request.service';
import { Client } from '../../models/client.model';
import { ClientService } from '../../services/client-service/client.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { User } from '../../models/user.model';
import { ActivatedRoute } from '@angular/router';

/**
 * Component for the Associate List page
 * @author Alex, Xavier
 */
@AutoUnsubscribe
@Component({
  selector: "app-associate-list",
  templateUrl: "./associate-list.component.html",
  styleUrls: ["./associate-list.component.css"]
})

export class AssociateListComponent implements OnInit {
  //our collection of associates and clients
  associates: Associate[];
  clients: Client[];
  curriculums: Set<string>; //stored unique curriculums

  //used for filtering
  searchByStatus = "";
  searchByClient = "";
  searchByText = "";
  searchByCurriculum = "";
  searchByVerification = "";

  //status/client to be updated
  updateShow = false;
  updateStatus = "";
  updateClient: number;
  updateVerification: string;
  updated = false;

  //used for ordering of rows
  desc = false;
  sortedColumn = "";

  //user access data - controls what they can do in the app
  user: User;
  canUpdate = false;

  /**
   * Inject our services
   * @param associateService
   * @param rs
   */
  constructor(
    private associateService: AssociateService,//TfAssociate,
    private clientService: ClientService,
    private rs: RequestService,
    private activated: ActivatedRoute
  ) {
    this.curriculums = new Set<string>();
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem("currentUser"));
    if (this.user.tfRoleId === 1 || this.user.tfRoleId === 2) {
      this.canUpdate = true; // let the user update data if user is admin or manager
    }
    this.getAllAssociates(); //grab associates and clients from back end
    this.getClientNames();

    //if navigating to this page from clicking on a chart of a different page, set default filters
    const paramMap = this.activated.snapshot.paramMap;
    const CliOrCur = paramMap.get("CliOrCur");
    const name = paramMap.get("name");
    const mapping = paramMap.get("mapping");
    const status = paramMap.get("status");
    if (CliOrCur) {//if values passed in, search by values
      if (CliOrCur === "client") {
        this.searchByClient = name;
      }
      else if (CliOrCur === "curriculum") {
        this.searchByCurriculum = name;
      }
      this.searchByStatus = mapping.toUpperCase() + ": " + status.toUpperCase();
    }
  }

  /**
   * Set our array of all associates
   */
  getAllAssociates() {
    const self = this;

    this.associateService.getAllAssociates().subscribe(data => {
      this.associates = data;
      console.log(this.associates);

      for (const associate of this.associates) {//get our curriculums from the associates
        this.curriculums.add(associate.curriculumName);

        if (associate.batchName === 'null') {
          associate.batchName = 'None'
        }
      }
      this.curriculums.delete("");
      this.curriculums.delete("null");
      self.sort("id"); //sort associates by ID
    });
  }

  /**
   * Fetch the client names
   */
  getClientNames() {
    this.clientService.getAllClients().subscribe(data => {
      this.clients = data;
    });
  }

  /**
   * Sort the array of clients based on a given input.
   * @param property to be sorted by
   */
  sort(property) {
    this.desc = !this.desc;
    let direction;
    if (property !== this.sortedColumn || this.updated) {
      //if clicking on new column sort ascending always, otherwise descending
      direction = 1;
    }
    else { direction = this.desc ? 1 : -1; }

    this.sortedColumn = property; //current column being sorted

    if (this.updated) {
      this.updated = false;
    }

    //sort the elements
    this.associates.sort(function (a, b) {
      if (a[property] < b[property]) { return -1 * direction; }
      else if (a[property] > b[property]) { return 1 * direction; }
      else { return 0; }
    });
  }

  /**
   * Bulk edit feature to update associate's verification, statuses and clients.
   */
  updateAssociates() {
    const ids: number[] = [];
    let i = 1;
    const self = this;

    for (i; i <= this.associates.length; i++) { //grab the checked ids
      const check = <HTMLInputElement>document.getElementById("" + i);
      if (check != null && check.checked) {
        ids.push(i);
      }
    }
    this.associateService.updateAssociates(ids, this.updateVerification, Number(this.updateStatus), this.updateClient).subscribe(
      data => {
        self.getAllAssociates(); //refresh the associates to reflect the updates made on DB
        self.updated = true;
      })
  }
}

import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associates-service/associates-service';
import { Associate } from '../../models/associate.model';
import { RequestService } from '../../services/request-service/request.service';
import { Client } from '../../models/client.model';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';

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
  searchByStatus: string = "";
  searchByClient: string = "";
  searchByText: string = "";
  searchByCurriculum: string = "";

  //status/client to be updated
  updateShow: boolean = false;
  updateStatus: string = "";
  updateClient: number;
  updated: boolean = false

  //used for ordering of rows
  desc: boolean = false;
  sortedColumn: string = "";

  public test: number[];

  constructor(
    private associateService: AssociateService,
    private rs: RequestService
  ) {
    this.curriculums = new Set<string>();
  }

  ngOnInit() {
    //get current url
    this.getAllAssociates();
    this.getClientNames();

    var url = window.location.href.split("/");
    if(url.length==8)//if values passed in, search by values
    {
      if(url[4]=="client")
      {
        this.searchByClient=url[5];
      }
      else if(url[4]=="curriculum")
      {
        this.searchByCurriculum=url[5];
      }
       this.searchByStatus=url[6].toUpperCase()+",  "+url[7].toUpperCase();
    }
  }

  /**
   * Set our array of all associates
   */
  getAllAssociates() {
    let self = this;
    this.rs.getAssociates().subscribe(data => {
      this.associates = data;

      for (let associate of this.associates) {//get our curriculums
        this.curriculums.add(associate.curriculumName);

        if (associate.batchName === 'null') {
          associate.batchName = 'None'
        }
      }
      this.curriculums.delete("");
      this.curriculums.delete("null");
      self.sort("id");
    });
  }

  /**
   * Fetch the client names
   */
  getClientNames() {
    this.rs.getClients().subscribe(data => {
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
    if (property !== this.sortedColumn || this.updated)
      //set ascending or descending
      direction = 1;
    else direction = this.desc ? 1 : -1;

    this.sortedColumn = property;

    if (this.updated) this.updated = false;

    //sort the elements
    this.associates.sort(function (a, b) {
      if (a[property] < b[property]) return -1 * direction;
      else if (a[property] > b[property]) return 1 * direction;
      else return 0;
    });
  }

  /**
   * Bulk edit feature to update associate's statuses and clients.
   */
  updateAssociates() {
    var ids: number[] = [];
    var i = 1;
    let self = this;

    for (i; i <= this.associates.length; i++) { //grab the checked ids
      var check = <HTMLInputElement>document.getElementById("" + i);
      if (check != null && check.checked) {
        ids.push(i);
      }
    }
    this.associateService.updateAssociates(ids, this.updateStatus, this.updateClient).subscribe(
      data => {
        self.getAllAssociates();
        self.updated = true;
      })
  }
}

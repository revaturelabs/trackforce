import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model';
import { RequestService } from '../../services/request-service/request.service';
import { Client } from '../../models/client.model';
import { ClientService } from '../../services/client-service/client.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { User } from '../../models/user.model';
import { ActivatedRoute } from '@angular/router';
import { Curriculum } from '../../models/curriculum.model';

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
  public associates: Associate[];
  public clients: Client[];
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
  updateClient= "";
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
    if (this.user.role === 1 || this.user.role === 3 ||this.user.role===4) {
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
    this.associateService.getAllAssociates().subscribe(
      data => {
        this.associates = data;
        for (const associate of this.associates) {//get our curriculums from the associates
          if(associate.batch!==null && associate.batch.curriculumName!==null){
            this.curriculums.add(associate.batch.curriculumName.name);
          }
          if (associate.batch && associate.batch.batchName === 'null') {
            associate.batch.batchName = 'None'
          }
        }
        this.curriculums.delete("");
        this.curriculums.delete("null");
      }
    );
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
   * Bulk edit feature to update associate's verification, statuses and clients.
   */
  updateAssociates() {
    const ids: number[] = [];

    let associateList: Associate[] = [];
    for (const a of this.associates) { //grab the checked ids
      const check = <HTMLInputElement>document.getElementById("" + a.id);
      if (check != null && check.checked) {
        ids.push(a.id);
        // This line USED to be used to automatically approve an account.
        // Logan commented this out 08/09/2018 to check how this functionality should be implemented. 
        //a.user.isApproved = 1;
        associateList.push(a);
      }
    }

    if(this.updateVerification==="") {this.updateVerification="0";}
    if(this.updateStatus==="") {this.updateStatus="0";}
    if(this.updateClient==="") {this.updateClient="0";}
    this.associateService.updateAssociates(ids, Number(this.updateVerification), Number(this.updateStatus), Number(this.updateClient)).subscribe(
      data => {
        this.getAllAssociates(); //refresh the associates to reflect the updates made on DB
        this.updated = true;
      });
  }
}

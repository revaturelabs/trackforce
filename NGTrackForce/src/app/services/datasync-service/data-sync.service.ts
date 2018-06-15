import { Injectable } from '@angular/core';
import { Associate } from '../../models/associate.model';
import { Client } from '../../models/client.model';
import { Batch } from '../../models/batch.model';
import { Curriculum } from '../../models/curriculum.model';
import { MarketingStatus } from '../../models/marketing-status.model';
import { RequestService } from '../request-service/request.service';
import { BatchService } from '../batch-service/batch.service';
import { AssociateService } from '../associate-service/associate.service';
import { ClientService } from '../client-service/client.service';
import { SkillsetService } from '../skill-set-service/skill-set.service';
import { Observable , BehaviorSubject} from 'rxjs';
// import 'rxjs/Rx';

/**
 * @author Seán Vaeth
 *
 * This component requires some documentation
 * Datasync is bootstrapped with the app. This means it will run on ng serve before components
 * that use it are initialized. It also has the benefit of being singleton across the app,
 * as opposed to providers being prototyped.
 *
 * The purpose of the datasync service is to synchronize all data across the app for all sessions
 * with the data read in from the database containing Salesforce data. DSS will quietly run in the
 * background of any component dependent on server side data and update accordingly.
 * DSS also ensures only one thread is used to hit the server side in background methods.
 * All other requests are prompted by the logged user with appropriate permissions
 *
 * This allows the application to run faster using cached data pulled in from the server at set intervals
 *
 *
 *
 *
 * Reviewed by Max
 * Not currently being used, but this is a good idea
 * Note made 6/6/2018
 */

const ASSOC_TIMEOUT = 30000;
const BATCH_TIMEOUT = 30000;
const CLIENT_TIMEOUT = 30000;
const CURRI_TIMEOUT = 30000;
const MARKET_TIMEOUT = 30000;

const ASSOC_INT = 2000;
const BATCH_INT = 2000;
const CLIENT_INT = 2000;
const CURRI_INT = 2000;
const MARKET_INT = 2000;

@Injectable()
export class DataSyncService {

  // special auth token. This will only work as long as TestAdmin is a valid user
  private token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0QWRtaW4iLCJleHAiOjE1MTU4ODA0NTB9.8n2K4gYpDCzIxgOASTI467f1HZtbIkGB16-eb0atSXI";

  // specify storage containers for Salesforce data
  private clientStorage: BehaviorSubject<Client[]>;
  private associateStorage: BehaviorSubject<Associate[]>;
  private batchStorageById: BehaviorSubject<Batch[]>;
  private batchStorageByDate: BehaviorSubject<Batch[]>;
  private curriculumStorage: BehaviorSubject<Curriculum[]>;
  private marketingStorage: BehaviorSubject<MarketingStatus[]>;

  // inject RequestService for handling requests to the server
  constructor(
    private rs: RequestService,
    private bs: BatchService,
    private cs: ClientService,
    private as: AssociateService,
    private ss: SkillsetService
  ) {
    // fetch data on initialization
    this.fetchData();
  }

  private checkFetchData() {
  }

  /**
   * @function fetchData
   *
   * Fetch data from server
   * this method populates our caches
   * and begins the data monitoring observables
   */
  private fetchData() {

    // get data on load
    this.fetchAssociateStorage();
    this.fetchBatchStorageSortedById();
    this.fetchBatchStorageSortedByDate();
    this.fetchClientStorage();
    this.fetchCurriculumStorage();
    this.fetchMarketingStorage();
  }

  /**
   *  getters and setters
   *  Getters: gets data from server using http requests then subscribes to storage updates
   *  Setters: stores data to storage units then subscribes on more data input
   *            notifies observers of change to stored data
   * */
  //

  private setAssociateStorage(data: any) {
    const newStorage = this.associateStorage = data;
    // this.associateStorage.next(newSt orage);
  }

  public fetchAssociateStorage() {
    this.as.getAllAssociates().subscribe(data => {
      // this.setAssociateStorage(data);
    });
  }

  private setClientStorage(data: any) {
    const newStorage = this.clientStorage = data;
    // this.clientStorage.next(newStorage);
  }

  public fetchClientStorage() {
    this.cs.getAllClients().subscribe(data => {
      // this.setClientStorage(data);
    })
  }

  private setBatchStorageSortedById(data: any) {
    const newStorage = this.batchStorageById = data;
    // this.batchStorageById.next(newStorage);
  }

  public fetchBatchStorageSortedById() {
    this.bs.getBatchesSortedById().subscribe(data => {
      // this.setBatchStorageSortedById(data);
    })
  }

  private setBatchStorageSortedByDate(data: any) {
    const newStorage = this.batchStorageByDate = data;
    // this.batchStorageByDate.next(newStorage);
  }

  public fetchBatchStorageSortedByDate() {
    this.bs.getBatchesSortedByDate().subscribe(data => {
      // this.setBatchStorageSortedByDate(data);
    })
  }

  private setCurriculumStorage(data: any) {
    const newStorage = this.curriculumStorage = data;
    // this.curriculumStorage.next(newStorage);
  }

  public fetchCurriculumStorage() {
    this.ss.getAllCurricula().subscribe(data => {
      // this.setCurriculumStorage(data);
    })
  }

  private setMarketingStorage(data: any) {
    this.marketingStorage = data;
    this.marketingStorage.subscribe(() => {

    });
  }

  public fetchMarketingStorage() {
    this.rs.getStatuses().subscribe(data => {
      // this.setMarketingStorage(data);
    })
  }
}

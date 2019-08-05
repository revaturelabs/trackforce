import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, AsyncSubject, BehaviorSubject } from 'rxjs';

import { Associate } from '../../models/associate.model';
import { environment } from '../../../environments/environment';
import { Interview } from '../../models/interview.model';
import { GraphCounts } from '../../models/graph-counts';
import { LocalStorageUtils } from '../../constants/local-storage';
import { of } from 'rxjs/observable/of';
import { Helpers } from '../../lsHelper';
import { Trainer } from '../../models/trainer.model';

/**
 * Service for retrieving and updating data relating to associates.
 * @author Alex, Xavier
 */
@Injectable()
export class AssociateService {
  private baseURL: string = environment.url + 'TrackForce/associates';
  private nassURL: string = this.baseURL + '/nass';

  /**
   * These behavior subjects will hold an initial empty value until a request is sent for their
   * data. This should not be the way it is implemented however since we have to remain compatible
   * with the components written already sending a null value from an AsyncSubject would throw many errors
   *
   * Each of the private variables below represents the return of each of the functions that are in this
   * service this needs to be refactored but in this first pass of the service revamp top priority is to
   * stop having the data reload each time and allow the application to hold a copy of the data to load
   * then update in the background.
   *
   * NOTE: The $ on a variable mean it is an Observable
   *
   * TODO: switch to using an AsyncSubject
   *
   * Note on things to do that would then allow this
   * 1. In the dependant components null checking will be needed
   * 2. Once components can handle a null value switch the BehaviorSubject to an Async Subject
   * 3. Ideally the components will show loading, error, and success by listening to the three
   *    events that a subject will output when subscribed to
   */
  private allAssociates$: BehaviorSubject<Associate[]> = new BehaviorSubject<Associate[]>([]);

  private associateCount$: BehaviorSubject<number[]> = new BehaviorSubject<number[]>([]);

  private updateAssociates$: AsyncSubject<boolean> = new AsyncSubject<boolean>();

  private updateAssociate$: AsyncSubject<boolean> = new AsyncSubject<boolean>();

  private approveAssociate$: AsyncSubject<boolean> = new AsyncSubject<boolean>();

  private getAssociatesByStatus$: AsyncSubject<GraphCounts[]> = new AsyncSubject<GraphCounts[]>();

  private getUndeployedAssociates$: AsyncSubject<GraphCounts[]> = new AsyncSubject<GraphCounts[]>();

  private currentAssociateSnapshot$: BehaviorSubject<Associate[]> = new BehaviorSubject<Associate[]>([]);
  private currentIndex = 0;
  private withLimit = 20;
  private currentClientFilter = "";
  private currentStatusFilter = "";
  private currentTextFilter = "";
  private currentFirstName = "";
  private currentLastName = "";
  public hasReceivedEndForCurrentFilter = false;

  // TODO: Decide if empty strings is better or if a loading message should be put here
  // TODO: Why is there a get by user and get by associate this is two different models on backend
  // ? should one be handled by another service
  // ? Do we really need two in this section of the frontend
  private associateByUserId$: BehaviorSubject<Associate> = new BehaviorSubject<Associate>(new Associate(
    "",
    "",
    null
  ));

  private associateByAssociateId$: BehaviorSubject<Associate> = new BehaviorSubject<Associate>(new Associate(
    "",
    "",
    null
  ));

  constructor(private http: HttpClient, public lsHelp: Helpers) {}

  /**
   *
   * Gets all of the associates
   */
  getAllAssociates(): BehaviorSubject<Associate[]> {
    let key: string = LocalStorageUtils.CACHE_ASSOCIATE_ALL;

    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      const url: string = this.baseURL + '/allAssociates';
      this.http.get<Associate[]>(url).subscribe(
        (data: Associate[]) => {
          this.allAssociates$.next(data);
          localStorage.setItem(key, JSON.stringify(data));
        },
        error => this.allAssociates$.error(error)
      );
      return this.allAssociates$;
    } else {
      this.allAssociates$.next(JSON.parse(localStorage.getItem(key)))
    }

    return this.allAssociates$;
  }

  /**
   * get the count of the associates to display in the pie charts on the home page
   */
  getCountAssociates(): BehaviorSubject<number[]> {
    let key: string = LocalStorageUtils.CACHE_ASSOCIATE_COUNT

    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      const url: string = this.baseURL + '/countAssociates';
      this.http.get<number[]>(url).subscribe(
        (data: number[]) => {
          this.associateCount$.next(data)
          localStorage.setItem(key, JSON.stringify(data));
        },
        error => this.associateCount$.error(error)
      );

      return this.associateCount$;
    } else {
      let count: number[] = JSON.parse(localStorage.getItem(key));
      return new BehaviorSubject<number[]>(count);
    }
  }

  /**
   *
   * Get specific associate by user id
   * @param id - the user id of the user object of an associate to retrieve
   */
  getAssociateByUserId(id: number) {
    let key: string = LocalStorageUtils.CACHE_ASSOCIATE_BY_USER_ID + id

    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      const url: string = this.baseURL + '/' + id;
      this.http.get<Associate>(url).subscribe(
        (data: Associate) => {
          this.associateByUserId$.next(data);
          localStorage.setItem(key, JSON.stringify(data));
        },
        error => this.associateByUserId$.error(error)
      )
      return this.associateByUserId$;
    } else {
      return of(JSON.parse(localStorage.getItem(key)))
    }
  }

  /**
   *
   * Get specific associate by associate id
   * @param id - the associate id of an associate object
   */
  getByAssociateId(id: number) {
    const url: string = this.baseURL + '/associates/' + id;
    this.http.get<Associate>(url).subscribe(
      (data: Associate) => this.associateByAssociateId$.next(data),
      error => this.associateByAssociateId$.error(error)
    );
    return this.associateByAssociateId$;
  }

  /**
   *
   * Update the given associate's status/client
   * @param ids - list of associate ids of associates to be updated
   * @param marketingStatusId - the marketing status these associates will be updated to
   * @param clientId - the client id that the associates will be mapped to
   *
   * ? Changing this to use BehaviorSubjects however it may be wanted to have it not multiplex
   */
  updateAssociates(ids: number[], verification: number, marketingStatusId: number, clientId: number): Promise<boolean> {
    return new Promise((resolve, reject) => {
      const url: string = this.baseURL + '?marketingStatusId=' + marketingStatusId + '&clientId=' + clientId + '&verification=' + verification;
      this.http.put<boolean>(url, ids).subscribe(
        (data) => {
          resolve(data);
        },
        (error) => {
          reject(error)
        }
      );
    });
  }

  /**
   *
   * This method updates the associate in the database
   * @param associate - the associate object with the updated values
   * @returns Promise - Promise that resolves true on a 200, or an error message on 400/500
   */
  updateAssociate(associate: any) {
    return new Promise((resolve, reject)=> {
      const url: string = this.baseURL + '/update/' + associate.id;
      this.http.put<boolean>(url, associate).subscribe(
        // resolve true for a successful return since we don't care unless it's not a 200
        data => resolve(true),
        // reject on error
        error => reject(error)
      );
    });
  }

  // ? Below this point the requests are for stats which may need to become a seperate service
  // For now that decision can be left to the service revamp for now the service update will
  // focus on keeping one copy of data to aid performance

  getAssociatesByStatus(statusId: number): Observable<GraphCounts[]> {
    const url: string = this.baseURL + '/mapped/' + statusId;
    let key: string = LocalStorageUtils.CACHE_ASSOCIATE_BY_STATUS + "|" + '/mapped/' + statusId;
    let count: BehaviorSubject<GraphCounts[]>  = new BehaviorSubject<GraphCounts[]>([])

    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      this.http.get<GraphCounts[]>(url).subscribe(
        (data: GraphCounts[]) => {
          count.next(data);
          localStorage.setItem(key, JSON.stringify(data));
        },
        error => this.currentAssociateSnapshot$.error(error)
      );
    } else {
      count.next(JSON.parse(localStorage.getItem(key)))
    }

    return count;
  }

  /**
   * Returns a promise that resolves to either true for any 200, or an error object for any 400/500
   */
  approveAssociate(associateID: number) {
    return new Promise((resolve, reject)=> {
      const url: string = this.baseURL + '/' + associateID + '/approve';
      this.http.put<boolean>(url, associateID).subscribe(
        data => resolve(true),
        error => reject(error)
      );
    });
  }

  getUndeployedAssociates(mappedOrUnmapped: string): Observable<GraphCounts[]> {
    const url: string = this.baseURL + '/undeployed/' + mappedOrUnmapped;
    let key: string = LocalStorageUtils.CACHE_ASSOCIATE_PAGE + "|" + '/undeployed/' + mappedOrUnmapped;
    let count: BehaviorSubject<GraphCounts[]>  = new BehaviorSubject<GraphCounts[]>([])

    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      this.http.get<GraphCounts[]>(url).subscribe(
        (data: GraphCounts[]) => {
          count.next(data);
          localStorage.setItem(key, JSON.stringify(data));
        },
        error => this.currentAssociateSnapshot$.error(error)
      );
    } else {
      count.next(JSON.parse(localStorage.getItem(key)))
    }

    return count;
  }

  getAssociateSnapshot() {
    return this.currentAssociateSnapshot$;
  }

  fetchAssociateSnapshot(limit: number, filter): BehaviorSubject<Associate[]> {
    this.hasReceivedEndForCurrentFilter = false;
    this.withLimit = limit;
    this.currentIndex = 0;

    this.currentClientFilter = filter.client || "";
    this.currentStatusFilter = filter.status || "";
    this.currentTextFilter = filter.sortText || "";
    this.currentFirstName = filter.firstName || "";
    this.currentLastName = filter.lastName || "";

    // Base route
    let queryParams = `/page?startIndex=${this.currentIndex}&numResults=${this.withLimit}`;


    // Determine filters if any
    const {status, client, sortText, firstName, lastName} = filter;

    if (status) {
      queryParams += `&mStatusId=${status}`;
    }
    if (client) {
      queryParams += `&clientId=${client}`;
    }
    if (sortText) {
      queryParams += `&sortText=${sortText}`;
    }
    if (firstName && lastName) {
      queryParams += `&firstName=${firstName}&lastName=${lastName}`;
    }

    return this.fetchCachedSnapshot(queryParams);
  }

  fetchNextSnapshot() {
    if (this.hasReceivedEndForCurrentFilter) {
      return this.currentAssociateSnapshot$;
    }

    this.currentIndex += this.withLimit;
    // Base route
    let queryParams = `/page?startIndex=${this.currentIndex}&numResults=${this.withLimit}`;

    if (this.currentStatusFilter) {
      queryParams += `&mStatusId=${this.currentStatusFilter}`;
    }
    if (this.currentClientFilter) {
      queryParams += `&clientId=${this.currentClientFilter}`;
    }
    if (this.currentTextFilter) {
      queryParams += `&sortText=${this.currentTextFilter}`;
    }

    return this.fetchCachedSnapshot(queryParams);
  }

  fetchCachedSnapshot(queryParams: string): BehaviorSubject<Associate[]> {
    const url: string = this.baseURL + queryParams;
    let key: string = LocalStorageUtils.CACHE_ASSOCIATE_PAGE + "|" + queryParams

    
    /*So the following if statement is in here because 
    * there was an issue with the filter where after clearing 
    * the filters there would be a value in local storage. 
    * Having values in local storage for some reason prevents
    * the result list from being populated with results so whenever
    * localstorage has a value make it null to prevent 
    * filter functionality from breaking. 
    */
    if(localStorage.getItem(key) !== null)
    {
      localStorage.removeItem(key);
    }
    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      this.http.get<Associate[]>(url).subscribe(
        (data: Associate[]) => {
          this.currentAssociateSnapshot$.next(data);
          localStorage.setItem(key, JSON.stringify(data));

          if (!data) {
            this.hasReceivedEndForCurrentFilter = true;
          }
        },
        error => this.currentAssociateSnapshot$.error(error)
      );
    } else {
      this.currentAssociateSnapshot$.next(JSON.parse(localStorage.getItem(key)))
    }

    return this.currentAssociateSnapshot$;
  }

  fetchAssociateSnapshotT(limit: number, filter): BehaviorSubject<Associate[]> {
    this.hasReceivedEndForCurrentFilter = false;
    this.withLimit = limit;
    this.currentIndex = 0;

    this.currentClientFilter = filter.client || "";
    this.currentStatusFilter = filter.status || "";
    this.currentTextFilter = filter.sortText || "";
    this.currentFirstName = filter.firstName || "";
    this.currentLastName = filter.lastName || "";

    // Base route
    let queryParams = `/pagetrain?startIndex=${this.currentIndex}&numResults=${this.withLimit}`;


    // Determine filters if any
    const {status, client, sortText, firstName, lastName} = filter;

    if (status) {
      queryParams += `&mStatusId=${status}`;
    }
    if (client) {
      queryParams += `&clientId=${client}`;
    }
    if (sortText) {
      queryParams += `&sortText=${sortText}`;
    }
    if (firstName && lastName) {
      queryParams += `&firstName=${firstName}&lastName=${lastName}`;
    }
    const trainer: Trainer = JSON.parse(this.lsHelp.localStorageItem("currentTrainer"));

    queryParams += `&trainerId=${trainer.id}`;

    return this.fetchCachedSnapshot(queryParams);
  }

  fetchNextSnapshotT() {
    if (this.hasReceivedEndForCurrentFilter) {
      return this.currentAssociateSnapshot$;
    }

    this.currentIndex += this.withLimit;
    // Base route
    let queryParams = `/pagetrain?startIndex=${this.currentIndex}&numResults=${this.withLimit}`;

    if (this.currentStatusFilter) {
      queryParams += `&mStatusId=${this.currentStatusFilter}`;
    }
    if (this.currentClientFilter) {
      queryParams += `&clientId=${this.currentClientFilter}`;
    }
    if (this.currentTextFilter) {
      queryParams += `&sortText=${this.currentTextFilter}`;
    }
    const trainer: Trainer = JSON.parse(this.lsHelp.localStorageItem("currentTrainer"));

    queryParams += `&trainerId=${trainer.id}`;
    return this.fetchCachedSnapshot(queryParams);
  }

  fetchCachedSnapshotT(queryParams: string): BehaviorSubject<Associate[]> {
    const url: string = this.baseURL + queryParams;
    let key: string = LocalStorageUtils.CACHE_ASSOCIATE_PAGE + "|" + queryParams

    
    /*So the following if statement is in here because 
    * there was an issue with the filter where after clearing 
    * the filters there would be a value in local storage. 
    * Having values in local storage for some reason prevents
    * the result list from being populated with results so whenever
    * localstorage has a value make it null to prevent 
    * filter functionality from breaking. 
    */
    if(localStorage.getItem(key) !== null)
    {
      localStorage.removeItem(key);
    }
    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      this.http.get<Associate[]>(url).subscribe(
        (data: Associate[]) => {
          this.currentAssociateSnapshot$.next(data);
          localStorage.setItem(key, JSON.stringify(data));

          if (!data) {
            this.hasReceivedEndForCurrentFilter = true;
          }
        },
        error => this.currentAssociateSnapshot$.error(error)
      );
    } else {
      this.currentAssociateSnapshot$.next(JSON.parse(localStorage.getItem(key)))
    }

    return this.currentAssociateSnapshot$;
  }
}

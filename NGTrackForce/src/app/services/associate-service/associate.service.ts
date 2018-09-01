import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, AsyncSubject, BehaviorSubject } from 'rxjs';

import { Associate } from '../../models/associate.model';
import { environment } from '../../../environments/environment';
import { Interview } from '../../models/interview.model';
import { GraphCounts } from '../../models/graph-counts';

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

  constructor(private http: HttpClient) {}

  /**
   *
   * Gets all of the associates
   */
  getAllAssociates(): BehaviorSubject<Associate[]> {
    const url: string = this.baseURL + '/allAssociates';
    this.http.get<Associate[]>(url).subscribe(
      (data: Associate[]) => this.allAssociates$.next(data),
      error => this.allAssociates$.error(error)
    );
    return this.allAssociates$;
  }

  /**
    gets initial associates loaded

    ! DEPRECATED: This is not a good idea to try to speed up performance
    ! the plan will be to implement pagination for now the app will just
    ! wait for all associates as this request will end up taking just as
    ! long due to the problems on the server side
  */
  getNAssociates(): Observable<Associate[]> {
    return this.http.get<Associate[]>(this.nassURL);
  }
  /**
   * get the count of the associates to display in the pie charts on the home page
   */
  getCountAssociates(): BehaviorSubject<number[]> {
    const url: string = this.baseURL + '/countAssociates';
    this.http.get<number[]>(url).subscribe(
      (data: number[]) => this.associateCount$.next(data),
      error => this.associateCount$.error(error)
    );
    return this.associateCount$;
  }

  /**
   *
   * Get specific associate by user id
   * @param id - the user id of the user object of an associate to retrieve
   */
  getAssociate(id: number) {
    const url: string = this.baseURL + '/' + id;
    this.http.get<Associate>(url).subscribe(
      (data: Associate) => this.associateByUserId$.next(data),
      error => this.associateByUserId$.error(error)
    );
    return this.associateByUserId$;
  }

  /**
   *
   * Get specific associate by associate id
   * @param id - the user id of the user object of an associate to retrieve
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
  updateAssociates(ids: number[], verification: number, marketingStatusId: number, clientId: number): Observable<boolean> {
    const url: string = this.baseURL + '?marketingStatusId=' + marketingStatusId + '&clientId=' + clientId + '&verification=' + verification;
    this.http.put<boolean>(url, ids).subscribe(
      (data) => {
        this.updateAssociates$.next(data);
      },
      (error) => {
        this.updateAssociates$.error(error);
      }
    );

    return  this.updateAssociates$;
  }


  /**
   *
   * This method updates the associate in the database
   * @param associate - the associate object with the updated values
   * @returns Promise - Promise that resolves true on a 200, or an error message on 400/500
   */
  updateAssociate(associate: any) {
    return new Promise((resolve, reject)=> {
      const url: string = this.baseURL + '/' + associate.id;
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
    this.http.get<GraphCounts[]>(this.baseURL + '/mapped/' + statusId).subscribe(
      (data: GraphCounts[]) => this.getAssociatesByStatus$.next(data),
      error => this.getAssociatesByStatus$.error(error)
    );
    return this.getAssociatesByStatus$;
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
    this.http.get<GraphCounts[]>(url).subscribe(
      (data: GraphCounts[]) => this.getUndeployedAssociates$.next(data),
      error => this.getUndeployedAssociates$.error(error)
    );
    return this.getUndeployedAssociates$;
  }

  getAssociateSnapshot() {
    return this.currentAssociateSnapshot$;
  }

  fetchAssociateSnapshot(limit: number, filter) {
    // Base route
    let queryParams = `/page`;
    

    // Determine filters if any
    const {status, client} = filter;
    if (status) {
      queryParams += `?mStatusId=${status}`;
    }
    if (client) {
      queryParams += `&clientId=${client}`;
    }

    // Make initial request
    const url: string = this.baseURL + queryParams;
    this.http.get<Associate[]>(url).subscribe(
      (data: Associate[]) => this.currentAssociateSnapshot$.next(data),
      error => this.currentAssociateSnapshot$.error(error)
    );
    return this.currentAssociateSnapshot$;
  }
}

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
   * data. The boolean will track if the first set of data is ready. This should not be the way
   * it is implemented however since we have to remain compatible with the components written
   * already sending a null value from an AsyncSubject would throw many errors
   *
   * TODO: Stop using the boolean and switch to using an AsyncSubject
   *
   * Note on things to do that would then allow this
   * 1. In the dependant components null checking will be needed
   * 2. Once components can handle a null value switch the BehaviorSubject to an Async Subject
   * 3. Ideally the components will show loading, error, and success by listening to the three
   *    events that a subject will output when subscribed to
   */
  private allAssociates$: BehaviorSubject<Associate[]> = new BehaviorSubject<Associate[]>([]);

  private associateCount$: BehaviorSubject<number[]> = new BehaviorSubject<number[]>([]);

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
    this.http.get<Associate[]>(url).subscribe((data: Associate[]) => this.allAssociates$.next(data));
    return this.allAssociates$;
  }

  /*
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
    this.http.get<number[]>(url).subscribe((data: number[]) => this.associateCount$.next(data));
    return this.associateCount$;
  }

  /**
   *
   * Get specific associate by user id
   * @param id - the user id of the user object of an associate to retrieve
   */
  getAssociate(id: number) {
    const url: string = this.baseURL + '/' + id;
    this.http.get<Associate>(url).subscribe((data: Associate) => this.associateByUserId$.next(data));
    return this.associateByUserId$;
  }

  /**
   *
   * Get specific associate by associate id
   * @param id - the user id of the user object of an associate to retrieve
   */
  getByAssociateId(id: number) {
    const url: string = this.baseURL + '/associates/' + id;
    this.http.get<Associate>(url).subscribe((data: Associate) => this.associateByAssociateId$.next(data));
    return this.associateByAssociateId$;
  }

  /**
   *
   * Update the given associate's status/client
   * @param ids - list of associate ids of associates to be updated
   * @param marketingStatusId - the marketing status these associates will be updated to
   * @param clientId - the client id that the associates will be mapped to
   */
  updateAssociates(ids: number[], verification: number, marketingStatusId: number, clientId: number): Observable<boolean> {
    const url: string = this.baseURL + '?marketingStatusId=' + marketingStatusId + '&clientId=' + clientId + '&verification=' + verification;
    return this.http.put<boolean>(url, ids);
  }

  /**
   *
   * This method updates the associate in the database
   * @param associate - the associate object with the updated values
   */
  updateAssociate(associate: any) {
    const url: string = this.baseURL + '/' + associate.id;
    return this.http.put<boolean>(url, associate);
  }

  getAssociatesByStatus(statusId: number): Observable<GraphCounts[]> {
    return this.http.get<GraphCounts[]>(this.baseURL + '/mapped/' + statusId);
  }

  approveAssociate(associateID: number) {
    const url: string = this.baseURL + '/' + associateID + '/approve';
    return this.http.put<boolean>(url, associateID);
  }

  getUndeployedAssociates(mappedOrUnmapped: string): Observable<GraphCounts[]> {
    const url: string = this.baseURL + '/undeployed/' + mappedOrUnmapped;
    return this.http.get<GraphCounts[]>(url);
  }
}

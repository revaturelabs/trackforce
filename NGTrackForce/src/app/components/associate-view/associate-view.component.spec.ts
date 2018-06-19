import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AssociateViewComponent} from './associate-view.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {AssociateService} from '../../services/associate-service/associate.service';
import {ActivatedRoute} from '@angular/router';
import {RequestService} from '../../services/request-service/request.service';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {NO_ERRORS_SCHEMA} from '@angular/core';
// added imports; DK
import { ClientService } from '../../services/client-service/client.service';

export class MockActivatedRoute {
  static createMockRoute(tid: number): any {
    return {
      params: Observable.of({id: tid}),
      snapshot: {
        parent: {
          params: {
            id: 1
          }
        },
        paramMap: {
          get(name: string): string {
            return '' + tid;
          }
        }
      },
    };
  }
}

describe('AssociateViewComponent', () => {
  let component: AssociateViewComponent;
  let fixture: ComponentFixture<AssociateViewComponent>;
  let clients: Array<any> = [];

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AssociateViewComponent
      ],
      providers: [
        AuthenticationService,
        RequestService,
        AssociateService,
        ClientService,
        {provide: ActivatedRoute, useValue: MockActivatedRoute.createMockRoute(1)}
      ],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      schemas: [NO_ERRORS_SCHEMA]
    });
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an associate', () => {
    expect(component.associate).toBeTruthy();
  });

  it('getAssociate() should return the specified associate', () => {
    expect(component.getAssociate(900)).toContain("Cameron");
    expect(component.getAssociate(90000)).toContain(null);
  });

  it('getClient() should return a list of clients', () => {
    component.getClients();
    expect(clients.length).toBeGreaterThanOrEqual(0);
    expect(clients.toString).toContain("Ciox Health");
  });
});

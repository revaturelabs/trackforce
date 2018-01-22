import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AssociateViewComponent} from './associate-view.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {AssociateService} from '../../services/associates-service/associates-service';
import {ActivatedRoute} from '@angular/router';
import {RequestService} from '../../services/request-service/request.service';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {NO_ERRORS_SCHEMA} from '@angular/core';

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

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AssociateViewComponent
      ],
      providers: [
        AuthenticationService,
        RequestService,
        AssociateService,
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
});

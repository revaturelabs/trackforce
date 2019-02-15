import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import { InterviewDetailsComponent } from './interview-details.component';

import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { InterviewService } from '../../services/interview-service/interview.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { ActivatedRoute } from '../../testing-helpers/router-stubs';
import {RequestService} from '../../services/request-service/request.service';
import { HttpClient, HttpHandler } from '../../../../node_modules/@angular/common/http';
import { MockActivatedRoute, MockAuthenticationService, MockInterviewService } from '../../testing-helpers/test-mock-services'

describe('InterviewDetailsComponent', () => {
  let component: InterviewDetailsComponent;
  let fixture: ComponentFixture<InterviewDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      declarations: [ InterviewDetailsComponent ],
      imports: [ FormsModule, RouterTestingModule],
      providers: [
        RequestService,
        HttpClient,
        HttpHandler,
        {provide: InterviewService, useClass: MockInterviewService},
        {provide: AuthenticationService, useClass: MockAuthenticationService},
        {provide: ActivatedRoute, useValue: MockActivatedRoute.createMockRoute(1)}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InterviewDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

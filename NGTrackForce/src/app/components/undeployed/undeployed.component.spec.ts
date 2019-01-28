import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {ChartsModule} from 'ng2-charts';
//import { appRoutes } from '../../routing/app-routing.module'
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { UndeployedComponent } from './undeployed.component';
import { AssociateService } from '../../services/associate-service/associate.service';
import { MockAssociateService } from '../../testing-helpers/test-mock-services'

import { HttpClient, HttpHandler } from '@angular/common/http';

describe('UndeployedComponent', () => {
  let component: UndeployedComponent;
  let fixture: ComponentFixture<UndeployedComponent>;
  let routes = [
      {
          path: '',
          component: UndeployedComponent
      }
  ]

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UndeployedComponent],
      imports: [ChartsModule, RouterTestingModule.withRoutes(routes)],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [HttpClient, HttpHandler, {provide: AssociateService, useClass: MockAssociateService}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UndeployedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

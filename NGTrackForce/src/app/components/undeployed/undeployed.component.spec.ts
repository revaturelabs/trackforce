import { MockAssociateService } from './../associate-view/associate-view.component.spec';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {ChartsModule} from 'ng2-charts';
//import { appRoutes } from '../../routing/app-routing.module'
import { Router, ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { UndeployedComponent } from './undeployed.component';
import { AssociateService } from '../../services/associate-service/associate.service';
import { HttpClient, HttpHandler } from '@angular/common/http';

describe('UndeployedComponent Test Suite', () => {
  let mockAssociateService = new MockAssociateService(null);
  let service = new AssociateService(null);
  actRouter:ActivatedRoute;
  let component: UndeployedComponent;
  let fixture: ComponentFixture<UndeployedComponent>;
  let spy: any;

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
      providers: [AssociateService,
         HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  //this test, as well as a few others, is still failing; clientUndeployedData is null, so the last expectation fails
  beforeEach(() => {
    spy = spyOn(service, 'getUndeployedAssociates').and.returnValues(mockAssociateService.mockData);
    fixture = TestBed.createComponent(UndeployedComponent);
    component = fixture.componentInstance;
    expect(component.loadChart).toHaveBeenCalled;
    expect(component.clientUndeployedData.length).toBeGreaterThan(0);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a div that contains a chart at the top of the page', ()=> {
    expect(component.clientUndeployedData).toBeTruthy();
    let el = fixture.debugElement.nativeElement;
    let canvas = el.querySelector('canvas');
    expect(canvas.id).toEqual('the_graph');
    
  });

  it('should have a pie chart at the bottom of the page when instantiated', () => {
    let el = fixture.debugElement.nativeElement;
    const canvas = el.querySelector('canvas');
    expect(canvas.id).toEqual('pie');
  });

  afterEach(() =>{
    component = null;
    spy = null;
    fixture = null;
  });
});

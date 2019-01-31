import { Observable } from 'rxjs';
import { MockAssociateService } from './../associate-view/associate-view.component.spec';
import { HttpClientTestingModule } from '@angular/common/http/testing/';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {ChartsModule} from 'ng2-charts';
import { DeployedComponent } from './deployed.component';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { AssociateService } from '../../services/associate-service/associate.service';
import { HttpClient, HttpHandler } from '@angular/common/http';

describe('DeployedComponent', () => {
  let mockAssociateService = new MockAssociateService(null);
  let component: DeployedComponent;
  let service = new AssociateService(null);
  let fixture: ComponentFixture<DeployedComponent>;
  let spy: any;

  let routes = [
      {
          path: '',
          component: DeployedComponent
      }
  ]

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeployedComponent ],
      imports: [ChartsModule, RouterTestingModule.withRoutes(routes), HttpClientTestingModule],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: AssociateService, useClass: MockAssociateService},
        HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeployedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    expect(component.clientDeployedData.length).toBeGreaterThan(0);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a graph at the top of the page when instantiated', () => {
    let el = fixture.debugElement.nativeElement;
    let canvas = el.querySelector('canvas');
    expect(canvas).toBeTruthy();
  });

  it('should have a pie chart at the bottom of the page', () => {
    let canvas = document.getElementById('pie');
    expect(canvas).toBeTruthy();
  });
});
